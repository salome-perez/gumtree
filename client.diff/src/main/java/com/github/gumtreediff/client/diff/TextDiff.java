/*
 * This file is part of GumTree.
 *
 * GumTree is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GumTree is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GumTree.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2011-2015 Jean-Rémy Falleri <jr.falleri@gmail.com>
 * Copyright 2011-2015 Floréal Morandat <florealm@gmail.com>
 */

package com.github.gumtreediff.client.diff;

import com.github.gumtreediff.actions.Diff;
import com.github.gumtreediff.actions.EditScript;
import com.github.gumtreediff.client.Option;
import com.github.gumtreediff.client.Register;
import com.github.gumtreediff.io.ActionsIoUtils;
import com.github.gumtreediff.matchers.MappingStore;
import com.github.gumtreediff.tree.TreeContext;
import com.github.gumtreediff.tree.Tree;
import com.github.gumtreediff.unparser.Unparser;
import com.github.gumtreediff.actions.model.Addition;
import com.github.gumtreediff.actions.model.Action;
import com.github.gumtreediff.actions.model.Delete;
import com.github.gumtreediff.actions.model.Insert;
import com.github.gumtreediff.actions.model.Move;
import com.github.gumtreediff.actions.model.Update;
import com.github.gumtreediff.actions.model.TreeAddition;
import com.github.gumtreediff.actions.model.TreeDelete;
import com.github.gumtreediff.actions.model.TreeInsert;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Register(name = "textdiff", description = "Dump actions in a textual format.",
        options = TextDiff.TextDiffOptions.class)
public class TextDiff extends AbstractDiffClient<TextDiff.TextDiffOptions> {
    public TextDiff(String[] args) {
        super(args);
        if (!Files.isRegularFile(Paths.get(opts.srcPath)))
            throw new Option.OptionException("Source must be a file: " + opts.srcPath, opts);
        if (!Files.isRegularFile(Paths.get(opts.dstPath)))
            throw new Option.OptionException("Destination must be a file: " + opts.dstPath, opts);

        if (opts.format == null) {
            opts.format = OutputFormat.TEXT;
            if (opts.output != null) {
                if (opts.output.endsWith(".json"))
                    opts.format = OutputFormat.JSON;
                else if (opts.output.endsWith(".xml"))
                    opts.format = OutputFormat.XML;
            }
        }
    }

    public static class TextDiffOptions extends AbstractDiffClient.DiffOptions {
        protected OutputFormat format;
        protected String output;

        @Override
        public Option[] values() {
            return Option.Context.addValue(super.values(),
                    new Option("-f", String.format("format: %s", Arrays.toString(OutputFormat.values())), 1) {
                        @Override
                        protected void process(String name, String[] args) {
                            try {
                                format = OutputFormat.valueOf(args[0].toUpperCase());
                            } catch (IllegalArgumentException e) {
                                throw new Option.OptionException(String.format(
                                        "No such format '%s', available formats are: %s\n",
                                        args[0].toUpperCase(), Arrays.toString(OutputFormat.values())), e);
                            }
                        }
                    },
                    new Option("-o", "output file", 1) {
                        @Override
                        protected void process(String name, String[] args) {
                            output = args[0];
                        }
                    }
            );
        }

        @Override
        void dump(PrintStream out) {
            super.dump(out);
            out.printf("format: %s\n", format);
            out.printf("output file: %s\n", output == null ? "<stdout>" : output);
        }
    }

    @Override
    protected TextDiffOptions newOptions() {
        return new TextDiffOptions();
    }

    protected String getMethod(Action a) {
        List<Tree> parents = a.getNode().getParents();
        String methodName = "";
        for (Tree parent : parents) {
            String currentNodeType = parent.getType().name;
            //String currentNodeLabel = parent.getLabel();
            if (currentNodeType.equals("MethodDeclaration")) {
                methodName = (String) parent.getMetadata("id");
                //String meta2 = (String) a.getNode().getRoot().getChild("0").getMetadata("id");
                //String meta2 = (String) parent.getMetadata(methodName);
                System.out.printf("%n%n%n______ METHOD _____: %s [NodeType]: %s", methodName,currentNodeType);
                //System.out.printf("%n\t [Meta2]: %s", meta2);           
            }
        }
        return methodName;
    }

    @Override
    public void run() throws Exception {
        Diff diff = getDiff();
        ActionsIoUtils.ActionSerializer serializer = opts.format.getSerializer(
                diff.src, diff.editScript, diff.mappings);
        if (opts.output == null)
            serializer.writeTo(System.out);
        else
            serializer.writeTo(opts.output);
        //System.out.println("Diff src");
        //System.out.println(diff.src);
        //System.out.println("Diff editScript");
        //System.out.println(diff.editScript);
        //System.out.println("Diff mappings");
        //System.out.println(diff.mappings);
        //PrettyPrint pp = new PrettyPrint();
        //pp.runUnparser();
        // --- Option 1 ---
        //Tree originalTree = diff.src.getRoot();
        //System.out.printf("--- Tree from Textdiff ---%n%s%n",originalTree.toString());
        //System.out.println("Children:");
        //List<Tree> children = originalTree.getChildren();
        //for (Tree child: children) {
        //    System.out.println(child.toString());
        //}
        //StringBuilder sb = new StringBuilder();
        //Unparser.prettyPrintNode(originalTree, sb);
        //String result = sb.toString();
        //System.out.printf("%n%n--- UNPARSED ---:%n%s",result);
        // --- Option 2 ---
        //System.out.printf("%n%n------------ LOOP OVER ACTIONS ... ");
        for (Action a : diff.editScript.asList()) {
            String actionMethod = getMethod(a);
            //Tree nodeTree = a.getNode().getParent(); // a.getNode().getRoot();
            Tree src = a.getNode();
            Tree dst = null;
            StringBuilder sb = new StringBuilder();
            if (actionMethod != "") {
                if (a instanceof Move) { 
                    dst = diff.mappings.getDstForSrc(src).getParent();
                } else if (a instanceof Update) {
                    dst = diff.mappings.getDstForSrc(src);
                } else if (a instanceof Insert) {
                    dst = a.getNode().getParent();
                    //if (dst.isRoot()) insertRoot((Insert) a, nodeTree); else 
                    //insertAction((Insert)a,nodeTree, dst.getParent().getChildPOsition(dst));
                } else if (a instanceof Delete) {
                    dst = null;
                    //deleteAction((Delete) a, nodeTree);
                } else if (a instanceof TreeInsert) {
                    dst = a.getNode().getParent();
                    //insertTreeAction((TreeInsert) a, nodeTree, dst.getParent(), dst.getParent().getChildPosition(dst))
                } else if (a instanceof TreeDelete) {
                    dst = null;
                    //deleteTreeAction((TreeDelete) a, nodeTree);
                }
            }
            if (dst != null) {
                System.out.printf("%n\t[Action]: %s", a.getName());
                Unparser.prettyPrintNode(dst,sb);
                String result = sb.toString();
                System.out.printf("%n\t[Action Unparsed]:%n%s",result);
            }
        }
    }

    enum OutputFormat {
        TEXT {
            @Override
            ActionsIoUtils.ActionSerializer getSerializer(TreeContext sctx, EditScript actions, MappingStore mappings)
                    throws IOException {
                return ActionsIoUtils.toText(sctx, actions, mappings);
            }
        },
        XML {
            @Override
            ActionsIoUtils.ActionSerializer getSerializer(TreeContext sctx, EditScript actions, MappingStore mappings)
                    throws IOException {
                return ActionsIoUtils.toXml(sctx, actions, mappings);
            }
        },
        JSON {
            @Override
            ActionsIoUtils.ActionSerializer getSerializer(TreeContext sctx, EditScript actions, MappingStore mappings)
                    throws IOException {
                return ActionsIoUtils.toJson(sctx, actions, mappings);
            }
        };

        abstract ActionsIoUtils.ActionSerializer getSerializer(TreeContext sctx, EditScript actions,
                                                               MappingStore mappings) throws IOException;
    }
}

package com.github.gumtreediff.unparser;

import com.github.gumtreediff.actions.model.Action;
import com.github.gumtreediff.actions.model.Delete;
import com.github.gumtreediff.actions.model.Move;
import com.github.gumtreediff.tree.Tree;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Created by @author @pillravi on 11/17/16.
 *
 * Originally created on 3/2/16 by @pillravi as part of PANDA.
 */


public final class ApplierUtil {

//    private ApplierUtil() {
//        /* private so no client code can instantiate this util class */
//    }

    public static Tree javaFileToTree(String fileName) {
        String fileContents = null;
        try {
            fileContents = readWholeFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return GumTreeUtil.getTreeFromDiff(fileContents);
    }

    public static String readWholeFile(String filename) throws IOException {
        FileInputStream file = new FileInputStream(filename);
        Scanner scanner = new Scanner(file).useDelimiter("\\Z");
        String fileString;
        if (scanner.hasNext()) {
            fileString = scanner.next();
        } else {
            throw new IOException("No content in file");
        }
        return fileString;
    } /* end readWholeFile */

    public static List<Action> removeRedundantActions(List<Action> actions) {

        List<Action> paredList = new ArrayList<>();
        for (Action element : actions) {
            paredList.add(element);
        }

        for (int i = 0; i < actions.size(); i++) {
            Action curAction = actions.get(i);
            Tree curNode = curAction.getNode();
	    NodeTypeEnum auxn = NodeTypeEnum.valueOf(curNode.getType().name);
            //int curNodeId = auxn.ordinal();
            String curNodeId = curNode.getType().name;
	    String curOperation = curAction.toString().split(" ")[0];

            /* Don't move a node into another node if it's already there. */
            if (curOperation.equals("MOV")) {
                Move move = (Move) curAction;
                Tree destination = move.getParent();
                List<Tree> destinationDescendants = destination.getDescendants();
                for (Tree descendant : destinationDescendants) {
                    if (descendant.isIsomorphicTo(curNode)
                            && descendant.getLabel().equals(curNode.getLabel())
			    && descendant.getType() == curNode.getType()) {
                        paredList.remove(curAction);
                        paredList.add(new Delete(curNode));
                    }
                }
            }

            for (Action otherAction : actions) {

                Tree otherNode = otherAction.getNode();
		NodeTypeEnum auxn2 = NodeTypeEnum.valueOf(otherNode.getType().name);
                //int otherNodeId = auxn2.ordinal();
                String otherNodeId = otherNode.getType().name;
		String otherOperation = otherAction.toString().split(" ")[0];

                Iterable<Tree> iterable = otherNode.preOrder();
                Iterator<Tree> iterator = iterable.iterator();

                if (curNodeId.equals(otherNodeId) /* Don't compare current action with itself! */
                        && curOperation.equals(otherOperation)) {
                    continue;
                }

                /* The two actions aren't redundant if their operations are different, so move on  */
                /* - Actually, they can be redundant. */
                if (!curOperation.equals(otherOperation)) {
                    continue;
                }



                while (iterator.hasNext()) {
                    Tree nextNode = iterator.next();
		    //NodeTypeEnum n3 = NodeTypeEnum.valueOf(nextNode.getType().name);
                    String n3 = nextNode.getType().name;
		    //int nextNodeId = n3.ordinal();
		    if (curNodeId.equals(n3)) {
                        paredList.remove(curAction);
                        break;
                    }
                }
            }
        }

        List<List<Integer>> allIds = new ArrayList<>(paredList.size());
        for (Action a : paredList) {
            List<Tree> descendants = a.getNode().getDescendants();
            List<Integer> ids = new ArrayList<>();
            for (Tree d : descendants) {
		NodeTypeEnum n4 = NodeTypeEnum.valueOf(d.getType().name);
                ids.add(n4.ordinal());
            }
            allIds.add(ids);
        }
        return paredList;
    } /* end removeRedundantActions */

} /* end class ApplierUtil */

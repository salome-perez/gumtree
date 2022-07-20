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
 * Copyright 2021 Jean-Rémy Falleri <jr.falleri@gmail.com>
 */

package com.github.gumtreediff.gen.jsacorn;

import com.github.gumtreediff.gen.ExternalProcessTreeGenerator;
import com.github.gumtreediff.gen.Register;
import com.github.gumtreediff.gen.Registry;
import com.github.gumtreediff.io.TreeIoUtils;
import com.github.gumtreediff.tree.TreeContext;

import java.io.*;

@Register(id = "js-acorn", accept = {"\\.js"}, priority = Registry.Priority.MAXIMUM)
public class JsAcornTreeGenerator extends ExternalProcessTreeGenerator {

    private static final String JS_ACORN_CMD
            = System.getProperty("gt.jsa.path", "jsparser");

    @Override
    public TreeContext generate(Reader r) throws IOException {
        String output = readStandardOutput(r);
        TreeContext ctx = TreeIoUtils.fromXml().generateFrom().string(output);
        return ctx;
    }

    public String[] getCommandLine(String file) {
        return new String[]{JS_ACORN_CMD, file};
    }
}

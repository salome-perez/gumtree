package com.github.gumtreediff.unparser;

import com.github.gumtreediff.tree.Tree;
//import diffapplier.ASTTest;
import java.io.IOException;

public class PrettyPrint{
	//private String testFilePath2 = DATAPATH + "src/testdata/prettyprint/PrettyPrintTestFile.java";

    public void runUnparser( ){
        //otherjava= SnakeYAMLDataFormat_3
        //String testFilePath = "/home/mperezrosero/Documents/GUMTREE/repos_awaymerge_100/left/SqlReturnUpdateCount_8.java";
        String testFilePath = "/diff/left/SqlReturnUpdateCount_8.java";

        String testFileContents = this.getFileContents(testFilePath);
	System.out.println("TestFileContents");
	System.out.println(testFileContents);

        Tree originalTree = GumTreeUtil.getTreeFromDiff(testFileContents);
        String unparsedFromTree = Unparser.unparse(originalTree);
        System.out.println(unparsedFromTree);
    }

    private String getFileContents(String filepath) {
        String testFileContents = null;
        try {
	    System.out.println(filepath);
            testFileContents = ApplierUtil.readWholeFile(filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testFileContents;
    }
}

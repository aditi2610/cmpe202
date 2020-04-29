
/***
 * Excerpted from "The Definitive ANTLR 4 Reference",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpantlr2 for more book information.
***/
// import ANTLR's runtime libraries
import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
//import org.antlr.v4.runtime.tree.gui.TreeViewer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.awt.image.BufferedImage;
//import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
// import org.apache.http.HttpEntity;
// import org.apache.http.HttpResponse;
// import org.apache.http.client.HttpClient;
// import org.apache.http.client.methods.HttpPost;
// import org.apache.http.entity.StringEntity;
// import org.apache.http.impl.client.HttpClientBuilder;
// import org.apache.http.impl.client.CloseableHttpClient;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Test {
    private class yUmlPayload
    {
        String dsl_text;

        yUmlPayload(String dsl_text) {
            this.dsl_text = dsl_text;
        }
    }

    private static final int BUFFER_SIZE = 4096;

    public static void main(String[] args) throws Exception {
        System.out.println("Argumnets are");
        //String arg[]  = args[0].split("\t");
       Console c = System.console();
           System.out.print("Enter Folder path: ");
           //String folder = c.readLine();
           System.out.print("Enter Folder path: ");
           String fileName = c.readLine();


        // create a CharStream that reads from standard inputFile folder = new
       //File folder = new File("umlparser/testStarbucks/");
            File folder = new File("umlparser/uml-parser-test-3/");
      //File folder = new File("umlparser/test/starbucks/src/main/java/starbucks/");
        File[] inputFiles = folder.listFiles();
        //TODO check null pointer in case no file
        Map<String, String> props = new HashMap<String, String>();
        TryLoader1 loader = new TryLoader1();
        for (File f : inputFiles) {
            if (f.isFile() && f.getName().endsWith(".java")) {
                //System.out.println("File is: " + f);
                CharStream input = CharStreams.fromFileName(f.toString());

                // create a lexer that feeds off of input CharStream
                JavaLexer lexer = new JavaLexer(input);


                // create a buffer of tokens pulled from the lexer
                CommonTokenStream tokens = new CommonTokenStream(lexer);

                // create a parser that feeds off the tokens buffer
                JavaParser parser = new JavaParser(tokens);

                ParseTree tree = parser.compilationUnit(); // begin parsing at init rule
                //System.out.println(tree.toStringTree(parser)); // print LISP-style tree
                ParseTreeWalker walker = new ParseTreeWalker();
                System.out.println();
                walker.walk(loader, tree); // walk parse tree


            }
           
        }

        StringBuffer sBuffer = new StringBuffer();

        // for (String s : loader.output) {
          
        //     System.out.println(s);
        // }
        //append space at the end to support YUML 
        Iterator<String> i = loader.output.iterator();
        while (i.hasNext()){
            sBuffer.append(i.next());
            if(i.hasNext()){
                sBuffer.append(" ");
            }
        }

        /** 
         * This code converts uml string to yuml understandable format and make REST POST call
         * 
        */

         String json_payload =
              "{" 
            + "  \"dsl_text\":\""
            +  sBuffer.toString()
            + "\"}"; 

        //System.out.println("payload: " + json_payload);

        URL url = new URL("https://yuml.me/diagram/scruffy/class/");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setDoOutput(true);
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = json_payload.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        String yumlFileName = "";

        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
            yumlFileName = response.toString();
        }

        yumlFileName = yumlFileName.replace(".svg", ".png");
        //System.out.println("yumlFileName: " + yumlFileName);

        try {
            downloadFile("https://yuml.me/" + yumlFileName, ".", fileName);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private static void downloadFile(String fileURL, String saveDir, String fileN) throws IOException {
        URL url = new URL(fileURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();

        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = "";
            String disposition = httpConn.getHeaderField("Content-Disposition");
            String contentType = httpConn.getContentType();
            int contentLength = httpConn.getContentLength();

            if (disposition != null) {
                // extracts file name from header field
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10, disposition.length() - 1);
                }
            } else {
                // extracts file name from URL
                fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1, fileURL.length());
            }
            //System.out.println("File Name: "+ fileName);
            //fileName = fileName.replace(".svg", ".jpeg");
            // System.out.println(" after File Name: " + fileName);
            // System.out.println("Content-Type = " + contentType);
            // System.out.println("Content-Disposition = " + disposition);
            // System.out.println("Content-Length = " + contentLength);
            System.out.println("fileName = " + fileName);

            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();
            String saveFilePath = saveDir + File.separator + fileN +".png";

            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);

            int bytesRead = -1;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream.close();
            inputStream.close();

            System.out.println("File downloaded");
        } else {
            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();
    }


}

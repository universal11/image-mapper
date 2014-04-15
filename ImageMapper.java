/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagemapper;

import gui.ava.html.image.generator.HtmlImageGenerator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author universal11
 */
public class ImageMapper {
    
    public static String readFile(String path) throws FileNotFoundException, IOException{
        File file = new File(path);
        String content = "";
        
        if(file.exists()){
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String contentLine;
            while( (contentLine = bufferedReader.readLine()) != null){
               content += contentLine;
            }
        }
        
        return content;
    }

    public static void main(String[] args){
        String inputPath = "";
        String outputPath = "";
        String name = "map";
        
        for(int i = 0; i < args.length; i++){
            switch(args[i]){
                case "-i":
                    inputPath = args[(i + 1)];
                    break;
                case "-o":
                    outputPath = args[(i + 1)];
                    break;
                case "-n":
                    name = args[(i + 1)];
                    break;
                default:
                    break;
            }
        }
        
        if(!inputPath.isEmpty() && !outputPath.isEmpty()){
            try {
                String input = ImageMapper.readFile(inputPath);
                HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
                imageGenerator.loadUrl("file://" + inputPath);
                
                File outputDirectory = new File(outputPath);
                if(!outputDirectory.exists()){
                    outputDirectory.mkdirs();
                }
                
                imageGenerator.saveAsImage(outputPath + "/" + name + ".png");
                imageGenerator.saveAsHtmlWithMap(outputPath + "/" + name + ".html", outputPath + "/" + name + ".png");
            } 
            catch (IOException ex) {
                Logger.getLogger(ImageMapper.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        
    }
    
}

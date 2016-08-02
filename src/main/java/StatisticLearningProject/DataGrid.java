/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StatisticLearningProject;
import com.google.common.collect.Lists;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author john
 */
public class DataGrid {
    
    private ArrayList<CSVRecord> records = null;
    private String loadError = "";
    
    public DataGrid(File f){
        Reader in = null;
        
        try
        {
            in = new FileReader(f.getAbsolutePath());
        }
        catch(FileNotFoundException e)
        {
            loadError = e.getMessage();
            return;
        }
                
        try{
           this.records = Lists.newArrayList(CSVFormat.EXCEL.parse(in));         
        }catch(IOException e){
            loadError = e.getMessage();
    
        }        
    }
    
    ArrayList<CSVRecord> getRecords(){
        return records;
    }
    
    public boolean isSuccess(){
        return ! loadError.isEmpty();
    } 
    
    String getLoadError(){
        return loadError;
    }
    
}

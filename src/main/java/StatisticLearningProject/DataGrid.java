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
import javax.swing.table.DefaultTableModel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author john
 */
public class DataGrid {
    
    private String loadError = "";
    private DefaultTableModel tableModel;
    
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
            ArrayList<CSVRecord> records = Lists.newArrayList(CSVFormat.EXCEL.parse(in)); 
            CSVRecord headerRec = records.remove(0);
            int columns = headerRec.size();
            String[] headers = new String[columns];
            for(int i=0; i < columns; i++){ 
               headers[i]= (String)headerRec.get(i);
            }
            int rows = records.size();
            Object[][] data = new Object[rows][columns];
            int count = 0;
            for(CSVRecord record: records){
                for(int i=0; i < columns; i++){
                   data[count][i]= record.get(i);
                }
                count++;
           }
           tableModel = new DefaultTableModel(data,headers);
        }catch(IOException e){
            loadError = e.getMessage();
    
        }        
    }
    
    public DefaultTableModel getTableModel(){
        return this.tableModel;
    }
    
    public boolean isSuccess(){
        return ! loadError.isEmpty();
    } 
    
    String getLoadError(){
        return loadError;
    }
    
}

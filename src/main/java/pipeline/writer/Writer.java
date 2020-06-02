package pipeline.writer;

import org.apache.spark.sql.Dataset;

public class Writer {

    public static<T> void write(Dataset<T> ds, String outputPath){
        ds.write().format("text").save(outputPath);
    }

}

package pipeline.reader;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.Encoders;
//import org.apache.spark.sql.Encoder;

public class Reader {

    public Reader(){}

    public static Dataset<RawRecord> read(String inputPath, SparkSession sparkSession){
        return sparkSession.read()
                .textFile(inputPath)
                .withColumn("filename", org.apache.spark.sql.functions.input_file_name())
                .as(Encoders.bean(RawRecord.class));
    }

}

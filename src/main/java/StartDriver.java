import org.apache.commons.codec.StringEncoder;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.*;
import pipeline.reader.RawRecord;
import pipeline.reader.Reader;
import pipeline.writer.Writer;
import scala.Function1;

public class StartDriver {

    public static void main(String[] args) {

        String inputPath = "hdfs://hadoop-master:9000/spark_demo_input/in_search_of_lost_time.txt";
        String outputPath = "hdfs://hadoop-master:9000/spark_demo_output";

        SparkConf sparkConf = new SparkConf()
                .setAppName("Apache Spark Demo")
                .setMaster("yarn")
        ;

        SparkSession sparkSession = SparkSession
                .builder()
                .config(sparkConf)
                .getOrCreate();

        Dataset<RawRecord> rawDS = sparkSession
                .read()
                .textFile(inputPath)
                .withColumn("filename", functions.input_file_name())
                .map(new MapFunction<Row, RawRecord>() {

                    @Override
                    public RawRecord call(Row row) throws Exception {
                        RawRecord result = new RawRecord();
                        result.setActualContent(row.getString(0));
                        result.setFilename(row.getString(1));
                        return result;
                    }
                }, Encoders.bean(RawRecord.class))
                .cache();

        System.out.println("number of rows: " + rawDS.count());

        rawDS
                .rdd()
                .saveAsTextFile(outputPath);

        sparkSession.stop();

    }

}

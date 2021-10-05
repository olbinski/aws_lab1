import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    private static final String BUCKET_NAME = "242503-bit-beat";
    private static final String FILE_NAME = "index.html";

    public static void main(String[] args) throws IOException {
        Region region = Region.US_EAST_1;
        S3Client s3 = S3Client.builder().region(region).build();

        s3.listBuckets();
        GetObjectRequest build = GetObjectRequest.builder().bucket(BUCKET_NAME).key(FILE_NAME).build();
        ResponseInputStream<GetObjectResponse> object = s3.getObject(build);

        BufferedReader reader = new BufferedReader(new InputStreamReader(object));

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }
}

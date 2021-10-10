import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class App {
    private static final String BUCKET_NAME = "242503-bit-beat";
    private static final String FILE_NAME = "index.html";

    public static void main(String[] args) {
        Region region = Region.US_EAST_1;
        S3Client s3 = S3Client.builder().region(region).build();

        s3.listBuckets();
        GetObjectRequest request = GetObjectRequest.builder().bucket(BUCKET_NAME).key(FILE_NAME).build();
        ResponseInputStream<GetObjectResponse> inputStream = s3.getObject(request);

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String html = reader.lines()
                    .collect(Collectors.joining());
            System.out.println(html);
        } catch(IOException e) {
            System.out.println("Connection with AWS failed");
        }
    }
}

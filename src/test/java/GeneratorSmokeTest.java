import com.amartus.utils.cmd.Generate;
import io.airlift.airline.Cli;
import io.airlift.airline.Help;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

public class GeneratorSmokeTest {
    private static final Logger log = LoggerFactory.getLogger(Generate.class);

    public static void main(String[] args) {
        Path mefPath = Paths.get("/projects/MEF-LSO-Sonata-SDK/");
        Path specPath  = mefPath.resolve("payload_descriptions/ProductSpecDescription/");

        args = new String[]{
                "generate",
                "-c",
                "configurations/spring-server-example.yaml",
//                "-p",
//                specPath.toString() + "/MEF_UNISpec_v3.json",
                "-i",
//                mefPath.toString() + "/api/Serviceability/MEF_api_productOfferingQualificationManagement_3.0.1.yaml"
                mefPath.toString() + "/api/ProductOrder/MEF_api_productOrderNotification_3.0.0.yaml"

        };

        String version = "1.0";
        Cli.CliBuilder<Runnable> builder =
                Cli.<Runnable>builder("openapi-generator-wrapper-cli")
                        .withDescription(
                                String.format(
                                        Locale.ROOT,
                                        "OpenAPI generator wrapper CLI (version %s).",
                                        version))
                        .withDefaultCommand(Help.class)
                        .withCommands(
                                Generate.class,
                                Help.class
                        );

        try {
            builder.build().parse(args).run();
        } catch (Exception e) {
            log.error("Error:", e);
            System.out.println(e.getMessage());
        }
    }
}

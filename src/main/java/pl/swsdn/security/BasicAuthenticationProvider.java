package pl.swsdn.security;

import com.google.gwt.thirdparty.guava.common.base.Splitter;
import com.google.gwt.thirdparty.guava.common.io.BaseEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.google.gwt.thirdparty.guava.common.base.Strings.nullToEmpty;

@Component
public class BasicAuthenticationProvider implements AuthenticationProvider {

    private final String secret;

    @Autowired
    public BasicAuthenticationProvider( @Value("${my.secret}") String secret) {
        this.secret = BaseEncoding.base64().encode(secret.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public boolean authenticate(String authentication) {
        List<String> basic = Splitter
                .on("Basic")
                .trimResults()
                .splitToList(nullToEmpty(authentication));
        return basic.size() == 2 && secret.equals(basic.get(1));
    }
}

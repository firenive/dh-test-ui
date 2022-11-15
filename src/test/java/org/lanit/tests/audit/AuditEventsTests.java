package org.lanit.tests.audit;

import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.lanit.components.Utilities;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Predicates.equalTo;

public class AuditEventsTests {

    @Test
    public void createAuditEvent() {
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            String uri = Utilities.readProperties("audit");

            HttpPost httpPost = new HttpPost(uri);

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username", "user"));
            params.add(new BasicNameValuePair("password", "password"));
            httpPost.setEntity((HttpEntity) new UrlEncodedFormEntity(params));

            CloseableHttpResponse response = client.execute(httpPost);
            client.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

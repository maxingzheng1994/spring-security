package com.mxz.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.removeAllMappings;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

/*作者：马兴争
 *日期: 2018年4月14日
 *时间： 下午1:12:03
 **/
public class MockServer {
	public static void main(String[] args) throws IOException {
		configureFor(8062);
		removeAllMappings();
		mock("/order/1", "01");
	}

	private static void mock(String url, String fileName) throws IOException {
		ClassPathResource resource = new ClassPathResource("mock/response/"+fileName+"txt");
		String input = FileUtils.readFileToString(new File("mock/response/1.json"), "UTF-8");
		String content = StringUtils.join(FileUtils.readLines(resource.getFile(), "UTF-8").toArray(), "\n");
		stubFor(get(urlPathEqualTo(url)).willReturn(aResponse().withBody(content).withStatus(200)));
	}
}

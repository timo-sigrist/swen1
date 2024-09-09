package ch.zhaw.swen1.iotdisplay.util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import ch.zhaw.swen1.iotdisplay.platform.Platform;
import ch.zhaw.swen1.iotdisplay.util.SntpProtocoll.Reply;

/**
 * Example code for fetching the current date/time from an SNTP server
 * @author fer
 *
 */
public class SntpFetchExample {
	private static final String defaultUrl = "//0.ch.pool.ntp.org:123";

	/**
	 * Example code for fetching the current time from a time server. 
	 * Hint : ch.zhaw.swen1.iotdisplay.platform.impl.PlatformForTestWithoutDisplay provides a 
	 *    Platform implementation for test purposes. 
	 * @param platform
	 * @return
	 * @throws IOException
	 */
	public static LocalDateTime fetchCurrentTime(Platform platform) throws IOException {
		// prepare request
		ByteBuffer request = SntpProtocoll.createRequest(0);
		// create reply buffer
		ByteBuffer replyBuffer = SntpProtocoll.createResponse();
		// send request and read reply
		platform.sendAndReceiveOverUdp(defaultUrl, request, replyBuffer, 1000);
		// decode reply
		Reply reply = SntpProtocoll.decodeReply(replyBuffer);
		// extract current time from reply without compensation of the network delay
		Instant instant = Instant.ofEpochMilli(reply.getTransmitTime());
		// construct a local date time with the default ZoneId
		LocalDateTime currentTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		
		return currentTime;
	}
}

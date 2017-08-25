package com.sram.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FfmpegUtils {

	private static final Logger logger = LoggerFactory
			.getLogger(FfmpegUtils.class);

	public static void main(String[] args) {
		FfmpegUtils.convert("D://moc//test.wmv", "d://moc//test.flv");
//		FfmpegUtils.catchImage("d://moc//test.wmv", "d://moc//test.jpg");
//		 FfmpegUtils.videoDurationInfo("D://moc//test.wmv");
		// System.out.println(FfmpegUtils.videoDurationInfo("D://moc//父亲.mp3"));

	}

	/**
	 * 功能函数
	 * 
	 * @param inputFile
	 *            待处理视频，需带路径
	 * @param outputFile
	 *            处理后视频，需带路径
	 * @return
	 */
	public static boolean convert(String inputFile, String outputFile) {
		if (!checkfile(inputFile)) {
			return false;
		}
		if (process(inputFile, outputFile)) {
			return true;
		}
		return false;
	}

	// 检查文件是否存在

	private static boolean checkfile(String path) {
		File file = new File(path);
		if (!file.isFile()) {
			return false;
		}
		return true;
	}

	/**
	 * 转换过程 ：先检查文件类型，在决定调用 processFlv还是processAVI
	 * 
	 * @param inputFile
	 * @param outputFile
	 * @return
	 */
	public static boolean process(String inputFile, String outputFile) {
		int type = checkContentType(inputFile);
		boolean status = false;
		if (type == 0) {
			status = convertToFLV(inputFile, outputFile);// 直接将文件转为flv文件
		}
		return status;
	}

	/**
	 * 检查视频类型
	 * 
	 * @param inputFile
	 * @return ffmpeg 能解析返回0，不能解析返回1
	 */
	private static int checkContentType(String inputFile) {
		String type = inputFile.substring(inputFile.lastIndexOf(".") + 1,
				inputFile.length()).toLowerCase();
		// ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
		if (type.equals("avi")) {
			return 0;
		} else if (type.equals("mpg")) {
			return 0;
		} else if (type.equals("wmv")) {
			return 0;
		} else if (type.equals("3gp")) {
			return 0;
		} else if (type.equals("mov")) {
			return 0;
		} else if (type.equals("mp4")) {
			return 0;
		} else if (type.equals("asf")) {
			return 0;
		} else if (type.equals("asx")) {
			return 0;
		} else if (type.equals("flv")) {
			return 0;
		}
		return 9;
	}

	/**
	 * ffmepg: 能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
	 * 
	 * @param inputFile
	 * @param outputFile
	 * @return
	 */
	public static boolean convertToFLV(String inputFile, String outputFile) {
		if (!checkfile(inputFile)) {
			return false;
		}
		List<String> commend = new java.util.ArrayList<String>();
		// 低精度
		//		commend.add("D://moc//ffmpeg-20150126-git-f994000-win64-static//bin//ffmpeg");
		commend.add("/usr/bin/ffmpeg");// centos

		// ffmpeg -loglevel quiet -i inputFile -ab 128 -acodec libmp3lame -ac 1
		// -ar 22050 -r 29.97 -b 512 -vol 1000 -y outputFile
		// 必须添加，否则转换会堵塞
		commend.add("-loglevel");
		commend.add("quiet");

		commend.add("-i");
		commend.add(inputFile);

		// 控制声音参数
		// -acodec aac 设定声音编码
		// -ac <数值> 设定声道数：1为单声道，2为立体声 -ac 2
		// -ar <采样率> 设定声音采样率(8000，11025，22050) -ar 22050
		// -ab <比特率> 设定声音比特率(-ac设为立体声时要以一半比特率来设置，比如192kbps的就设成96) -ab 96
		// -vol <百分比> 设定音量大小，比如设定200就会比原来的音量大2倍 -vol 200
		
		commend.add("-ab");
		commend.add("64");
		commend.add("-acodec"); // -acodec 音频流编码方式
		commend.add("libmp3lame");
		commend.add("-ac"); // -ab 音频流码率(默认是同源文件码率，也需要视codec而定)
		commend.add("2");
		commend.add("-ar"); // -ar
		commend.add("22050");
		
//		commend.add("-vol");
//		commend.add("1000");

		// 控制画面参数
		// -bitexac 使用标准比特率
		// -vcodec xvid 使用xvid压缩
		// -s <宽高比> 指定分辨率大小 -s 320*240
		// -r <数值> 帧速率(非标准数值会导致音画不同步【标准值为15或29.97】) -r 15
		// -b <比特率> 指定压缩比特率 -b 1500
		// -qmin <数值> 设定最小质量 -qmin 10
		// -qmax <数值> 与-qmin相反，可以与-qmin同时使用 -qmax 30
		// -sameq 使用与源视频相同的质量
		// commend.add("-vcodec");
		// commend.add("flv:vbitrate=200");
//		commend.add("-vcodec");
//		commend.add("x264lib");
		commend.add("-s");
		commend.add("1280*720");
		commend.add("-b");
		commend.add("384");
		commend.add("-r");
		commend.add("18");
		commend.add("-qscale");// 对于视频的qscale，属于动态码率，可以设置为4或6，4的质量比6的要高。
		commend.add("6");

		commend.add("-y");

		commend.add(outputFile);
		StringBuffer test = new StringBuffer();
		for (int i = 0; i < commend.size(); i++)
			test.append(commend.get(i) + " ");
		System.out.println(test);
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commend);
			Process process = builder.start();

			process.waitFor();
			// 等待执行完毕，再返回
			return true;
		} catch (Exception e) {
			logger.error("[" + inputFile + "文件转换失败：] " + e.getMessage());

			e.printStackTrace();

			return false;
		}		
	}
	
	
	public static void catchImage(String mediaFile, String imageFile) {
		
		List<String> commend = new java.util.ArrayList<String>();

		//	commend.add("D://moc//ffmpeg-20150126-git-f994000-win64-static//bin//ffmpeg");
		// windows
		commend.add("/usr/bin/ffmpeg");// centos
		commend.add("-loglevel");
		commend.add("quiet");

		commend.add("-i");
		commend.add(mediaFile);
		
		commend.add("-y");
		commend.add("-f");
		commend.add("image2"); 
		commend.add("-ss");
		commend.add("2"); 
		commend.add("-vframes");
		commend.add("1"); 
		commend.add("-s");
		commend.add("500*280");
		commend.add(imageFile);

		StringBuffer test = new StringBuffer();
		for (int i = 0; i < commend.size(); i++)
			test.append(commend.get(i) + " ");
		System.out.println(test);
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commend);
			Process process = builder.start();

			process.waitFor();

		} catch (Exception e) {
			logger.error("[" + mediaFile + "文件抓图失败：] " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static String videoDurationInfo(String inputPath) {

		List<String> commend = new java.util.ArrayList<String>();
		//		commend.add("D://moc//ffmpeg-20150126-git-f994000-win64-static//bin//ffmpeg");//
		// windows
		commend.add("/usr/bin/ffmpeg");// centos

		commend.add("-i");
		commend.add(inputPath);
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commend);
			builder.redirectErrorStream(true);
			Process p = builder.start();

			// 1. start
			BufferedReader buf = null; // 保存ffmpeg的输出结果流
			String line = null;
			// read the standard output

			buf = new BufferedReader(new InputStreamReader(p.getInputStream()));

			StringBuffer sb = new StringBuffer();

			while ((line = buf.readLine()) != null) {
				System.out.println(line);
				sb.append(line);
				continue;
			}
			p.waitFor();// 这里线程阻塞，将等待外部转换进程运行成功运行结束后，才往下执行
			// 1. end
			String regexDuration = "Duration: (.*), start: (.*), bitrate: (\\d*) kb\\/s";
			Pattern pattern = Pattern.compile(regexDuration);
			Matcher matcher = pattern.matcher(sb.toString());

			while (matcher.find()) {
				return matcher.group(1);
				// System.out.println("提取出播放时间  ===" + matcher.group(1));
				// System.out.println("开始时间        =====" + matcher.group(2));
				// System.out.println("bitrate 码率 单位 kb==" + matcher.group(3));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
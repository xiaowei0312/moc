/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.job;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sram.util.DateUtils;

/**
 * Job - 备份数据库
 * 
 * @author Sram Team
 * @version 1.0
 */
@Component("dbBackupJob")
@Lazy(false)
public class DatabaseJob {

	private String username;

	private String password;

	private String dbname;

	private String dumppath;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getDumppath() {
		return dumppath;
	}

	public void setDumppath(String dumppath) {
		this.dumppath = dumppath;
	}

	/**
	 * 备份数据库
	 */
	@Scheduled(cron = "${job.database_backup.cron}")
	public void backup() {
		String osName = System.getProperty("os.name");
		String dumpFile = DateUtils.formatDate(new Date(), "-")
				+ ".sql";
		if ("Linux".equals(osName)) {
			File file = new File(dumppath);
			if(!file.exists()){
				file.mkdirs();
			}
			
			String command = "mysqldump  -hlocalhost  -u" + username + " -p"
					+ password + " --default-character-set=utf8 --result-file="
					+ dumppath + dumpFile + " --extended-insert=false --databases " + dbname;
			try {
				Runtime.getRuntime().exec(new String[] { "sh", "-c", command });
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		DatabaseJob job = new DatabaseJob();
		job.backup();
	}

}
package com.sram.service.impl;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.sram.dao.TestpaperChapterDao;
import com.sram.dao.TestpaperDao;
import com.sram.entity.Testpaper;
import com.sram.entity.TestpaperChapter;
import com.sram.service.TestpaperChapterService;
@Service("testpaperChapterServiceImpl")
public class TestpaperChapterServiceImpl extends BaseServiceImpl<TestpaperChapter, Long> implements TestpaperChapterService{
	@Resource(name = "testpaperChapterDaoImpl")
	private TestpaperChapterDao testpaperChapterDao;
	
	@Resource(name = "testpaperChapterDaoImpl")
	public void setBaseDao(TestpaperChapterDao testpaperChapterDao) {
		super.setBaseDao(testpaperChapterDao);
	}
	
	public List<TestpaperChapter> findAll() {
		return testpaperChapterDao.findAll();
	}

	public List<TestpaperChapter> findTestpaperChapter(Testpaper testpaper) {
		return testpaperChapterDao.findTestpaperChapter(testpaper);
	}

	public List<Testpaper> findTestpaper(TestpaperChapter testpaperChapter) {
		return testpaperChapterDao.findTestpaper(testpaperChapter);
	}


	public List<TestpaperChapter> findTestpaperChapterListByTestpaperId(
			Long testpaperId) {
		return testpaperChapterDao.findTestpaperChapterListByTestpaperId(testpaperId);
	}

	
	public List<TestpaperChapter> findTestpaperChapterByUserAndTestpaper(Long testpaperId,Long userId) {
		return this.testpaperChapterDao.findTestpaperChapterByUserAndTestpaper(testpaperId, userId);
	}
}

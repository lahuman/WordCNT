package lahuman.wordcnt;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.ibm.icu.text.BreakIterator;

public class BreakIteratorTest {

	@Test
	public void breakerIteratorTest() {
		BreakIterator iterator = BreakIterator.getSentenceInstance();
		String source = "삼성 키보드와 크롬이 잘 안맞는 것일까? 펜으로 글씨를 쓰고 나면 날라간다\r\n" + 
				"근데 삼성 기본 브라우저는 잘된다.\r\n" + 
				"탭은 손 글씨가 편한데\r\n" + 
				"#은근안좋아";

		iterator.setText(source);
		int start = iterator.first();
		int senteceCount = 0;
		for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
			String sentenceStr = source.substring(start, end).trim();
			if ("".equals(sentenceStr)) {
				continue;
			}
			senteceCount++;
			System.out.println(sentenceStr);
		}

		assertThat(senteceCount, is(5));
	}
}

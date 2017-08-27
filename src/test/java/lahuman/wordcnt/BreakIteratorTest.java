package lahuman.wordcnt;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.ibm.icu.text.BreakIterator;

public class BreakIteratorTest {

	@Test
	public void breakerIteratorTest() {
		BreakIterator iterator = BreakIterator.getSentenceInstance();
		String source = "삼성 키보드와 크롬이 잘 안맞는 것일까? 펜으로 글씨를 쓰고 나면 날라간다\r\n" + 
				"근데 삼성 기본 브라우저는 잘된다.\r\n" + 
				"삼성의 탭은 손글씨가 편한데\r\n" + 
				"#은근안좋아";
		Map<String, Integer> findWordMap = new HashMap<String, Integer>() {{
			put("삼성", 0);
			put("글씨", 0);
		}};
		iterator.setText(source);
		int start = iterator.first();
		int senteceCount = 0;
		for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
			final String sentenceStr = source.substring(start, end).trim();
			if ("".equals(sentenceStr)) {
				continue;
			}
			senteceCount++;
			findWordMap.keySet().stream().forEach(s->{
				int wordCnt = sentenceStr.split(s).length-1;
				if(wordCnt > 0) {
					findWordMap.put(s, findWordMap.get(s)+wordCnt);
					System.out.print("[" + s + wordCnt+ "]");
				}
			});
			
			System.out.println(sentenceStr );
			
		}
		System.out.print("[");
		findWordMap.keySet().stream().forEach(s->{
			if(findWordMap.get(s) > 0)
				System.out.print(s + "="+ findWordMap.get(s) + ",");
		});
		System.out.println("]");
		assertThat(senteceCount, is(5));
		
	}
}

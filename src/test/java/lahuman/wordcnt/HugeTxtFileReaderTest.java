package lahuman.wordcnt;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import org.junit.Test;

public class HugeTxtFileReaderTest {

//	 @Test
	public void testWordRead() {
		String wordTxt = "C:\\data\\word-data\\word\\고등학교(전체).txt";
		Map<String, Integer> wordCnt = new HashMap<>();

		try (Stream<String> stream = Files.lines(Paths.get(wordTxt), Charset.forName("euc-kr"))) {
			stream.filter(s -> !"".equals(s.trim())).map(s -> s.split("\t")).forEach(array -> {
				Stream.of(array).filter(s -> !"".equals(s.trim()))
						// .forEach(System.out::println)
						.forEach(s -> wordCnt.put(s, 0));
			});
		} catch (IOException e) {
			e.printStackTrace();
		}

		assertThat(wordCnt.size(), is(442));
	}

	@Test
	public void testContentsReadAndWrite() {
		String contentsTxt = "C:\\data\\word-data\\contents\\chapter1-5.txt";
		final AtomicInteger count = new AtomicInteger();
		try (Stream<String> stream = Files.lines(Paths.get(contentsTxt), Charset.forName("euc-kr"))) {
			Stream<String> stm = stream
					.map(s->{
						count.incrementAndGet();
						return count.get()+ " # "+ s;
					});
			
			Files.write(Paths.get("C:\\data\\word-data\\ouput.txt"), (Iterable<String>)stm::iterator);
			
//			.forEach(s -> {
//				count.incrementAndGet();
//				System.out.println(count.get()+ " # "+ s);
//			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertThat(count.get(), is(656));
	}
	
	
}

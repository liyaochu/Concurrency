package com.lyc.concurrency;

import com.lyc.concurrency.example.snyc.RandomUtils;
import org.junit.Test;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ConcurrencyApplicationTests {

	@Test
	public void contextLoads() {

		int[] ints = RandomUtils.randomCommon(0, 101, 100);
		for (int anInt : ints) {
			System.out.println(anInt);
		}
	}

}

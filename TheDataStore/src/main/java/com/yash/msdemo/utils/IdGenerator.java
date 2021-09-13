package com.yash.msdemo.utils;

import java.util.Random;

/**
 * @author Yash Joshi
 * @date created on 12-Sep-2021
 */
public final class IdGenerator {

	public static int generate() {
		Random r = new Random();
		return r.ints(1, 1, 99999).map(it -> it + r.nextInt(9999)).sum();

	}

}

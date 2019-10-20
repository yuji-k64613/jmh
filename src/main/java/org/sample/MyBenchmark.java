/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.sample;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

//@Fork(value = 1, jvmArgs = { "-Xms1G", "-Xmx1G" })
//@Warmup(iterations = 2)
//@Measurement(iterations = 2)
@BenchmarkMode(Mode.Throughput)
//@BenchmarkMode(Mode.AverageTime)
//@State(Scope.Benchmark)
@State(Scope.Thread)
public class MyBenchmark {
	private double r = 100000000;
	private double t = 100000000;
	private long cnt = 0;

	public MyBenchmark() {
		System.out.println("MyBenchmark");
	}

	@Setup
	public void setup() {
		System.out.println("########## START ############");
	}

	@TearDown
	public void tearDown() {
		System.out.println("\n############ END ############");
		System.out.println("count = " + cnt);
	}

	@Benchmark
	// @Threads(3)
	public double testMethod0() throws InterruptedException {
		// cnt++;
		return Math.sqrt(r) + Math.sqrt(r + 1);
		// Thread.sleep(10);
	}

	@Benchmark
	// @Threads(3)
	public double testMethod1() throws InterruptedException {
		// cnt++;
		return Math.sqrt(r);
		// Thread.sleep(10);
	}

	@Benchmark
	// @Threads(3)
	public double testMethod2() {
		return 1.2;
	}

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(MyBenchmark.class.getSimpleName())
				.jvmArgs("-Xms1G", "-Xmx1G")
				.forks(1)
				.threads(1)
				.warmupIterations(1)
				.measurementIterations(1)
				.build();

		new Runner(opt).run();
	}
}

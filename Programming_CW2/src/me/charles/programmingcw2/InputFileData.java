package me.charles.programmingcw2;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

/**
 * A file line iterator
 * 
 * @author charles
 * 
 */
public class InputFileData implements Closeable, Iterable<String>, Iterator<String> {
	private BufferedReader br;

	public InputFileData(String filename) throws FileNotFoundException {
		br = new BufferedReader(new FileReader(filename));
	}

	@Override
	public void close() {
		try {
			br.close();
		} catch (IOException e) {
		}
	}

	@Override
	public Iterator<String> iterator() {
		return this;
	}

	private String buffer;
	private boolean stored = false;

	@Override
	public boolean hasNext() {
		try {
			buffer = br.readLine();
		} catch (IOException e) {
			stored = false;
			return false;
		}
		return stored = (buffer != null);
	}

	@Override
	public String next() {
		if (!stored || buffer == null)
			hasNext();
		stored = false;
		return buffer;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		return new StringBuilder().append("InputFileData(buffer=").append(buffer).append(stored ? ",awaiting recovery" : "previous line").append(")").toString();
	}
}

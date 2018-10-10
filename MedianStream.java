/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Fall 2017 
// PROJECT:          (p3 running median)
// FILE:             (MedianStream)
//
// TEAM:    (individual)
// Authors: (Be sure to check if programming pairs or teams are allowed)
// Author1: (Nati Solomon Fett, solomonfett@wisc.edu,9075183963,lec 001)
// Author2: (name2,email2,netID2,lecture number2)
//
// ---------------- OTHER ASSISTANCE CREDITS 
// Persons: Identify persons by name, relationship to you, and email. 
// Describe in detail the the ideas and help they provided. 
// 
// Online sources: stackExchange: priority queue, MaxHeap
//////////////////////////// 80 columns wide //////////////////////////////////
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;

public class MedianStream {

	private static final String PROMPT_NEXT_VALUE = "Enter next value or q to quit: ";
	private static final String MEDIAN = "Current median: ";
	private static final String EXIT_MESSAGE = "That wasn't a double or 'q'. Goodbye!";
	private static final String FNF_MESSAGE = " not found.";

	/**
	 * Use this format to ensure that double values are formatted correctly.
	 * Double doubleValue = 1412.1221132 System.out.printf(DOUBLE_FORMAT,
	 * doubleValue);
	 */
	private static final String DOUBLE_FORMAT = "%8.3f\n";

	private Double currentMedian;
	private MaxPQ<Double> maxHeap;
	private MinPQ<Double> minHeap;

	/**
	 * Override Default Constructor
	 *
	 * Initialize the currentMedian = 0.0 Create a new MaxPQ and MinPQ.
	 */
	public MedianStream() {
		this.currentMedian = 0.0;
		this.maxHeap = new MaxPQ<Double>();
		this.minHeap = new MinPQ<Double>();
	}

	/**
	 * This method is called if the user passes NO command line arguments. The
	 * method prompts the user for a double value on each iteration.
	 *
	 * If the input received is a double, the current median is updated. After
	 * each iteration, print the new current median using MEDIAN string as
	 * declared and initialized with the data members above.
	 *
	 * If the input is the character 'q', return from the method.
	 *
	 * If the input is anything else, then you print an error using EXIT_MESSAGE
	 * string as declared and initialized with the data members above and then
	 * return from the method.
	 *
	 * For the purposes of calculating the median, every input received since
	 * the beginning of the method execution is part of the same stream.
	 */
	private static void runInteractiveMode() {
		double median = 0.0;
		boolean keepGoing = true;
		Double input = 0.0;
		MedianStream stream = new MedianStream();
		Scanner scnr = new Scanner(System.in);
		while (keepGoing) {
			System.out.println(PROMPT_NEXT_VALUE);
			// while the user is inputing doubles, takes the double adds it to
			// the proper heap and prints the current median
			if (scnr.hasNextDouble()) {
				input = scnr.nextDouble();
				median = stream.getMedian(input);
				System.out.print(MEDIAN);
				System.out.printf(DOUBLE_FORMAT, median);
			}
			// when user preses q, and exit message is displayed
			else if (scnr.next().equals("q")) {
				System.out.println("Exiting");
				keepGoing = false;
			}
			// if the user doesnt input a double or q,  then an exit message
			//is displayed
			else {
				System.out.println(EXIT_MESSAGE);
				keepGoing = false;
			}
		}

	}

	/**
	 * This method is called if the user passes command line arguments. The
	 * method is called once for every filename passed by the user.
	 *
	 * The method reads values from the given file and writes the new median
	 * after reading each new double value to the output file.
	 *
	 * The name of the output file follows a format specified in the write-up.
	 *
	 * If the input file contains a non-double value, the program SHOULD NOT
	 * throw an exception, instead it should just read the values up to that
	 * point, write medians after each value up to that point and then return
	 * from the method.
	 *
	 * If a FileNotFoundException occurs, just print the string FNF_MESSAGE as
	 * declared and initialized with the data members above.
	 */
	private static void findMedianForFile(String filename) {
		try {
			Double fileInMed = 0.0;
			Double median = 0.0;
			MedianStream stream = new MedianStream();
			File file = new File(filename);
			String inFile;
			Scanner readIn = new Scanner(file);
			// 1st this code makes the output file
			//takes incoming file and removes the ".txt" 
			//replaces it with "_out.txt"
			inFile = filename.substring(0, filename.length() - 4);
			inFile = inFile.concat("_out.txt");
			File outFile = new File(inFile);
			// using a print writer to print to the output file
			PrintWriter write = new PrintWriter(inFile);
			// While the scanner has more data to read, the median of the 
			//values is calculated and it is then printed on an out-file
			while (readIn.hasNext()) {
				fileInMed = readIn.nextDouble();
				median = stream.getMedian(fileInMed);
				write.printf(DOUBLE_FORMAT, median);
				write.println();
			}
			write.close();
		} catch (IOException e) {
			System.out.print(filename + FNF_MESSAGE);
		}
	}

	/**
	 * YOU ARE NOT COMPULSORILY REQUIRED TO IMPLEMENT THIS METHOD.
	 *
	 * That said, we found it useful to implement.
	 *
	 * Adds the new temperature reading to the corresponding maxPQ or minPQ
	 * depending upon the current state.
	 *
	 * Then calculates and returns the updated median.
	 *
	 * @param newReading
	 *            - the new reading to be added.
	 * @return the updated median.
	 */
	private Double getMedian(Double newReading) {
		// add to min heap if the newREading is less than current median 
		if (newReading < currentMedian) {
			if (maxHeap.size() == minHeap.size()) {
				minHeap.insert(newReading);
				// since min heap has 1 more element than max heap, it is now the highest
				// priority 
				currentMedian = minHeap.getMax();
			}
			else if (minHeap.size() == maxHeap.size() - 1) {
				// makes both min heap and max heap equal to each other
				minHeap.insert(newReading);
				// takes the average of the highest priority elements in each heap
				currentMedian = (minHeap.getMax() + maxHeap.getMax()) / 2;
			}
			else if (minHeap.size() == maxHeap.size() + 1) {
				//removes an element from min heap to max heap
				maxHeap.insert(minHeap.removeMax());
				// makes both min heap and max heap equal to each other
				minHeap.insert(newReading);
				// takes the average of the highest priority elements in each heap
				currentMedian = (minHeap.getMax() + maxHeap.getMax()) / 2;
			}
		}

		// if newReading is greater than current median,
		//it will be put in max heap
		if (newReading > currentMedian) {
			if (maxHeap.size() == minHeap.size()) {
				maxHeap.insert(newReading);
				// since max heap has 1 more element than max heap, it is now the highest
				// priority 
				currentMedian = maxHeap.getMax();
			}
			else if (maxHeap.size() == minHeap.size() - 1) {
				// makes both min heap and max heap equal to each other
				maxHeap.insert(newReading);
				// takes the average of the highest priority elements in each heap
				currentMedian = (maxHeap.getMax() + minHeap.getMax()) / 2;
			}
			else if (maxHeap.size() == minHeap.size() + 1) {
				// takes an element out of max heap and add it to min
				minHeap.insert(maxHeap.removeMax());
				// makes both min heap and max heap equal to each other
				maxHeap.insert(newReading);
				// takes the average of the highest priority elements in each heap
				currentMedian = (maxHeap.getMax() + minHeap.getMax()) / 2;
			}
		}
		return currentMedian;
	}
	// DO NOT EDIT THE main METHOD.
	public static void main(String[] args) {
		// Check if files have been passed in the command line.
		// If no files are passed, run an infinite interactive loop taking a
		// double
		// input each time until "q" is entered by the user.
		// After each iteration of the loop, update and display the new median.
		if (args.length == 0) {
			runInteractiveMode();
		}
		// If files are passed in the command line, open each file.
		// For each file, iterate over all the double values in the file.
		// After reading each new double value, write the new median to the
		// corresponding output file whose name will be inputFilename_out.txt
		// Stop reading the file at the moment a non-double value is detected.
		else {
			for (int i = 0; i < args.length; i++) {
				findMedianForFile(args[i]);
			}
		}
	}
}



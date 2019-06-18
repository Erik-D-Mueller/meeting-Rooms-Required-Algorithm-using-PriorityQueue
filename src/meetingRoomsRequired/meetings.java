package meetingRoomsRequired;
import java.util.PriorityQueue;
import java.util.Random;
// Erik D Mueller's solution to solving how many meetings rooms are required

public class meetings {

	public static void main(String[] args) {

		// generate 2-dimensional simple array of meetings, begin time is first row and
		// end time is 2nd row
		// number of meetings and time-increments

		// After playing around with this, I discovered the answer is always around half
		// of the meetings and usually occurs about in the middle of the time-span
		int numMeetings = 300;
		int timeIncrements = 2;

		firstMethod(generateMeetingTimeArray(numMeetings, timeIncrements));

	}

	/**
	 * @param meetingTimesArray
	 */
	private static void firstMethod(int[][] meetingTimesArray) {

		System.out.println("Multidimensional Array Before Sorting: \n");
		System.out.println("Begin-Time          End-Time \n");

		for (int k = 0; k < meetingTimesArray.length; k++) {

			System.out.println("   " + meetingTimesArray[k][0] + "     ------>     " + meetingTimesArray[k][1] + "\n");

		}

		PriorityQueue<Integer> beginTimesQueue = new PriorityQueue<Integer>();
		PriorityQueue<Integer> endTimesQueue = new PriorityQueue<Integer>();

		for (int k = 0; k < meetingTimesArray.length; k++) {

			beginTimesQueue.add(meetingTimesArray[k][0]);
			endTimesQueue.add(meetingTimesArray[k][1]);

		}

		int maxSoFar = 0;
		int roomsReq = 0;

		int sel = 0;

		System.out.println(
				"---------------------------------------------------------------------------------------------");
		System.out.println(" Begin-Times-Queue       end-Times-Queue");
		System.out.println(
				"---------------------------------------------------------------------------------------------");

		while (!beginTimesQueue.isEmpty() || !endTimesQueue.isEmpty()) {

			System.out.println(); // add new line

			if (!beginTimesQueue.isEmpty()) { // beginTimesQueue will empty out before endtimes queue and the while
												// loop will be concluded at the start once both queues are empty,

				// so we don't need to worry about the endTimesQueue

				System.out.println("      " + beginTimesQueue.peek() + "                          "
						+ endTimesQueue.peek() + "             \n");

				if (endTimesQueue.peek() > beginTimesQueue.peek()) {
					sel = 1;
				}
				if (endTimesQueue.peek() < beginTimesQueue.peek()) {
					sel = -1;
				}
				if (endTimesQueue.peek() == beginTimesQueue.peek()) {
					sel = 0;
				}

				switch (sel)

				{

				case 0:
					System.out.println("Popping off:  " + beginTimesQueue.peek() + "  and " + endTimesQueue.peek());
					beginTimesQueue.poll();
					endTimesQueue.poll();
					break;

				case 1:
					roomsReq++;
					if (roomsReq > maxSoFar) { // only check if roomsReq has gotten bigger
						maxSoFar = roomsReq;
					}
					System.out.println("Popping off:  " + beginTimesQueue.peek());
					beginTimesQueue.poll();
					break;

				case -1:

					roomsReq--;

					System.out.println("Popping off:  " + endTimesQueue.peek());
					endTimesQueue.poll();
					break;

				}

				System.out.print("                                       |||  rooms required: " + roomsReq
						+ "   |||   Max rooms so far: " + maxSoFar + "\n");

			} else {

				// Technically the algorithm could end here if we only need to know the max
				// rooms, because after the last meeting has started the number of rooms being
				// used (and thus the max) cannot increase.
				// beginTimesQueue will empty out before endtimes queue and the while loop will
				// be concluded at the start once both queues are empty, so we don't need to
				// worry about an endTimesQueue null pointer exception

				roomsReq--;

				System.out.println("     empty                       " + endTimesQueue.peek() + "\n");
				System.out.println("Popping off:  " + endTimesQueue.peek());
				endTimesQueue.poll();

				System.out.print("                                      |||  rooms required: " + roomsReq
						+ "   |||   Max rooms so far: " + maxSoFar + "\n");

			}

			System.out.println("--------------------------------------------------------------------------");

		}

		System.out.println("Final Number of Rooms To Reserve For Day: " + maxSoFar);
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

	}

	private static int[][] generateMeetingTimeArray(int numMeetings, int timeIncrements) {

		// In this case we know the number of meetings at compile time so a simple Array
		int[][] meetingTimesArray;
		meetingTimesArray = new int[numMeetings][2];

		Random rand = new Random();

		int randomValueOne;
		int randomValueTwo;

		for (int k = 0; k < numMeetings; k++) {

			// Generate two random numbers between 1 and the number of time increments
			// input-variable
			randomValueOne = rand.nextInt(timeIncrements);
			randomValueTwo = rand.nextInt(timeIncrements);

			// If we coincidentally get same start and end time then increment one
			if (randomValueOne == randomValueTwo) {
				randomValueOne++;
			}

			meetingTimesArray[k][0] = Math.min(randomValueOne, randomValueTwo); // Make sure the smaller number goes in
																				// top row of multidimensional array
			meetingTimesArray[k][1] = Math.max(randomValueOne, randomValueTwo);

		}

		return meetingTimesArray;

	}

}
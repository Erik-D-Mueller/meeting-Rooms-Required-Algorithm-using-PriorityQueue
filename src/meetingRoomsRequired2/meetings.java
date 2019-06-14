package meetingRoomsRequired2;
import java.util.PriorityQueue;
import java.util.Random;
// Erik D Mueller's solution to solving how many meetings rooms are required

public class meetings {

	public static void main(String[] args) {

		// generate 2-dimensional simple array of meetings, begin time is first row and end time is 2nd row

		// number of meetings and time-increments
		
		int numMeetings = 5;
		int timeIncrements = 20;
		
		firstMethod(generateMeetingTimeArray(numMeetings, timeIncrements));

	}

	private static void firstMethod(int[][] meetingTimesArray) {

		System.out.println("Multidimensional Array Before Sorting: \n");
		System.out.println("Begin-Time          End-Time \n");

		for (int k = 0; k < meetingTimesArray.length; k++) {

			System.out.println("   "  + meetingTimesArray[k][0] + "     ------>     " + meetingTimesArray[k][1] + "\n");

		}

		PriorityQueue<Integer> beginTimesQueue = new PriorityQueue<Integer>();
		PriorityQueue<Integer> endTimesQueue = new PriorityQueue<Integer>();

		for (int k = 0; k < meetingTimesArray.length; k++) {

			beginTimesQueue.add(meetingTimesArray[k][0]);
			endTimesQueue.add(meetingTimesArray[k][1]);

		}

		int maxSoFar = 1;
		int roomsReq = 1;

		int sel = 0; // The linter complains if I don't initialize "sel" outside of the if statements

		System.out.println("---------------------------------------------------------------------------------------------");
		System.out.println(" Begin-Times-Queue       end-Times-Queue");
		System.out.println("---------------------------------------------------------------------------------------------");
		
		while (!beginTimesQueue.isEmpty() && !endTimesQueue.isEmpty()) {

			
			if(roomsReq>maxSoFar) { maxSoFar = roomsReq;}
			
			System.out.println("      "      +    beginTimesQueue.peek() + "                          " + endTimesQueue.peek() + "             |||  rooms required: " +  roomsReq + "   |||   Max rooms so far: " + maxSoFar + "\n");

				
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
			case 1:
				roomsReq++;
				System.out.println("Popping off:  " + beginTimesQueue.peek());
				beginTimesQueue.poll();
				break;

			case -1:
				roomsReq--;
				System.out.println("Popping off:  " + endTimesQueue.peek());
				endTimesQueue.poll();
				break;

			case 0:
				System.out.println(
						"About to pop off  " + beginTimesQueue.peek() + "  and " + endTimesQueue.peek());
				beginTimesQueue.poll();
				endTimesQueue.poll();
				break;

			}
			System.out.println("--------------------------------------------------------------------------");

		}

		
		System.out.println("Final Number of Rooms To Reserve For Day: "  + maxSoFar);
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

			// Generate two random numbers between 1 and the number of time increments input-variable
			randomValueOne = rand.nextInt(timeIncrements) + 1;
			randomValueTwo = rand.nextInt(timeIncrements) + 1;

			// If we coincidentally get same start and end time then increment one
			if (randomValueOne == randomValueTwo) {
				randomValueOne++;
			}

			meetingTimesArray[k][0] = Math.min(randomValueOne, randomValueTwo); // Make sure the smaller number goes in top row of multidimensional array
			meetingTimesArray[k][1] = Math.max(randomValueOne, randomValueTwo);

		}

		return meetingTimesArray;

	}

}

#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include "rngs.h"

#define MAX_TESTS 10000

int main() {

	int k[10] = { adventurer, council_room, feast, gardens, mine,
		remodel, smithy, village, baron, great_hall };

	int i, seed, tests;

	int handSizeFail = 0;
	int treasureCountFail = 0;
	int discardFail = 0;
	int discardFailWithHandSizeFail = 0;

	struct gameState pre, post;
/*DEBUG
	int testNo = 0;
*/
	int discardSimilarityCounter = 0;

	printf("Testing Adventurer with random number of players, deck sizes, discard sizes, and hand sizes.\n");

	for (tests = 0; tests < MAX_TESTS; tests++) {

/*DEBUG	
		testNo++;
		printf("Test number %d\n", testNo);
*/
		int numPlayers, firstDrawn, secondDrawn;

		int failHandSize = 0;
		int failTreasureCount = 0;

		int randCardGain = 0;
		int randDiscardGain = 0;
		int randHandGain = 0;

		numPlayers = rand() % (MAX_PLAYERS - 1) + 2;
		seed = rand();

		initializeGame(numPlayers, k, seed, &pre);

		// gain random number of cards to discard and deck
		randDiscardGain = rand() % MAX_DECK;
		randCardGain = rand() % MAX_DECK;
		randHandGain = rand() % MAX_HAND;
		
		for (i = 0; i < randDiscardGain; i++) {
			gainCard((rand() % 16 + 1), &pre, 0, 0);
		}

		for (i = 0; i < randCardGain; i++) {
			gainCard((rand() % 16 + 1), &pre, 1, 0);
		}

		// draw random number of cards		
		for (i = 0; i < randHandGain; i++) {
			drawCard(0, &pre);
		}

		memcpy(&post, &pre, sizeof(struct gameState));

		cardEffect(adventurer, 0, 0, 0, &post, 0, 0);
/*DEBUG
		if (pre.discardCount[0] == post.discardCount[0]) {
			printf("Discarded nothing\n");
		}
*/
		// see if 2 cards have been added
		if (pre.handCount[0] != post.handCount[0] - 2) {
			handSizeFail++;
			failHandSize = 1;
		}

		// see if the two drawn cards are treasures
		// skip if hand size is not +2
		if (failHandSize == 0) {
			firstDrawn = post.hand[0][post.handCount[0] - 2];
			secondDrawn = post.hand[0][post.handCount[0] - 1];

			if ((firstDrawn < 4 || firstDrawn > 6) || (secondDrawn < 4 || secondDrawn > 6)) {
				treasureCountFail++;
			}
			failTreasureCount = 1;
		}

		// test to see if discard is different
		// test to see if pre/post discard piles are the same
		for (i = 0; i < pre.discardCount[0]; i++) {
			if (pre.discard[0][i] == post.discard[0][i]) {
				discardSimilarityCounter++;
			}
		}
		// if number of cards in same discard position = previous discard size, nothing has been discarded 
		if (discardSimilarityCounter == pre.discardCount[0]) {
			// could be possible that player drew treasures and discarded nothing
			if (pre.deckCount[0] != post.deckCount[0] + 2) {
				discardFail++;
				if (failHandSize == 1) {
					discardFailWithHandSizeFail++;
				}
			}

		}
	}

	printf("Test Iterations: %d\n", MAX_TESTS);
	printf("Failed Hand Size Test: %d\n", handSizeFail);
	printf("Failed Treasures In Hand Test: %d\n", treasureCountFail);
	printf("Failed Discard Test: %d\n", discardFail);
	printf("Failed Discard Test because of Hand Size Test failure: %d\n", discardFailWithHandSizeFail);

	printf("Adventurer Test Complete.\n\n");
	return 0;
}
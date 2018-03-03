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
	int buyCountFail = 0;
	int otherHandSizeFail = 0;


	struct gameState pre, post;


	printf("Testing Council Room with random number of players, deck sizes, discard sizes, and hand sizes.\n");

	for (tests = 0; tests < MAX_TESTS; tests++) {

		int numPlayers;

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
			pre.discard[0][pre.discardCount[0] - 1] = (rand() % 16 + 1);
			pre.discardCount[0]++;
		}

		for (i = 0; i < randCardGain; i++) {
			pre.deck[0][pre.deckCount[0] - 1] = (rand() % 16 + 1);
		}

		// draw random number of cards		
		for (i = 0; i < randHandGain; i++) {
			drawCard(0, &pre);
		}

		gainCard(council_room, &pre, 2, 0);
		memcpy(&post, &pre, sizeof(struct gameState));

		cardEffect(council_room, 0, 0, 0, &post, (post.handCount[0] - 1), 0);

		// test that hand size is +4, council_room() discards 1 so total card count should be +3
		if (pre.handCount[0] != (post.handCount[0] - 3)) {
			handSizeFail++;
		}

		// test for +1 buy
		if (pre.numBuys != post.numBuys - 1) {
			buyCountFail++;
		}

		// test other players have +1 card
		for (i = 1; i < numPlayers; i++) {
			if (pre.handCount[i] != post.handCount[i] - 1) {
				otherHandSizeFail++;
				break;
			}
		}

	}

	printf("Test Iterations: %d\n", MAX_TESTS);
	printf("Failed Hand Size Test: %d\n", handSizeFail);
	printf("Failed Buy Count Test: %d\n", buyCountFail);
	printf("Failed Opponent Hand Count Test: %d\n", otherHandSizeFail);
	

	printf("Council Room Test Complete.\n\n");
	return 0;
}
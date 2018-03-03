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
	int discardFail = 0;


	struct gameState pre, post;


	printf("Testing Smithy with random number of players, deck sizes, discard sizes, and hand sizes.\n");

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
			pre.discard[0][pre.discardCount[0]-1] = (rand() % 16 + 1);
			pre.discardCount[0]++;
		}

		for (i = 0; i < randCardGain; i++) {
			pre.deck[0][pre.deckCount[0]-1] = (rand() % 16 + 1);
		}

		// draw random number of cards		
		for (i = 0; i < randHandGain; i++) {
			drawCard(0, &pre);
		}

		gainCard(smithy, &pre, 2, 0);
		memcpy(&post, &pre, sizeof(struct gameState));

		cardEffect(smithy, 0, 0, 0, &post, (post.handCount[0]-1), 0);

		// test that hand size is +3
		if (pre.handCount[0] != (post.handCount[0] - 2)) {
			handSizeFail++;
		}

		// test that smithy was discarded
		if (post.playedCards[post.playedCardCount-1] != smithy) {
			discardFail++;
		}
	
	}

	printf("Test Iterations: %d\n", MAX_TESTS);
	printf("Failed Hand Size Test: %d\n", handSizeFail);
	printf("Failed Discard Test: %d\n", discardFail);

	printf("Smithy Test Complete.\n\n");
	return 0;
}
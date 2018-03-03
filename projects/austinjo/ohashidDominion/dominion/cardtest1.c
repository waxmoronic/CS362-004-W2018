#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include "rngs.h"

void assertTrue(int ret, int exp) {
	if (ret == exp) printf("Passed! Return matches expected!\n\n");
	else printf("***FAILED: Return does not match, returned %d, expected %d\n\n", ret, exp);
}

int main() {

	int k[10] = { adventurer, council_room, feast, gardens, mine,
		remodel, smithy, village, baron, great_hall};
	
	int pre_hand_size, post_hand_size;

	struct gameState G, pre;

	printf("Testing Smithy.\n");

	initializeGame(2, k, 1, &G);
	memcpy(&pre, &G, sizeof(struct gameState));

	printf("Adding Smithy to player 0's hand.\n");
	gainCard(smithy, &G, 2, 0);
	
	printf("Playing Smithy.\n");
	playCard(5, -1, -1, -1, &G);

	printf("Test 1: Player has +3 cards.\n");
	assertTrue(post_hand_size = G.handCount[0] ,pre_hand_size = pre.handCount[0] + 3);

	printf("Test 2: Smithy was discarded.\n");
	if (G.playedCardCount == pre.playedCardCount + 1 && G.playedCards[G.playedCardCount-1]) printf ("Passed! One more card has been played and the last played card was a Smithy.\n");
	else printf("***FAILED: Expected %d cards played and last card played to be 13, %d cards played and last card was %d.\n", pre.playedCardCount+1, G.playedCardCount, G.playedCards[G.playedCardCount - 1]);

	printf("Test 3: Player has -1 action.\n");
	assertTrue(G.numActions, pre.numActions-1);

	return 0;
	}
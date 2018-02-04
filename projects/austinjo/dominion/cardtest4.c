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

	struct gameState G;

	printf("Testing Council Room.\n");

	initializeGame(2, k, 1, &G);

	printf("Adding Council Room to player 0's hand.\n\n");
	gainCard(council_room, &G, 2, 0);

	printf("Playing Council Room\n");
	playCard(5, -1, -1, -1, &G);
	
	printf("Test 1: Player 0 should have +4 cards.\n");
	assertTrue(G.handCount[0], 9);

	printf("Test 2: Player 0 should have +1 buy.\n");
	assertTrue(G.numBuys, 2);

	printf("Test 3: Player 1 should have +1 card.\n");
	assertTrue(G.handCount[1], 1); // Second player doesn't draw until first player's turn ends, so they should have 1 card at this point.

	printf("Test 4: Player 0 should have -1 action.\n");
	assertTrue(G.numActions, 0);

	return 0;
	}
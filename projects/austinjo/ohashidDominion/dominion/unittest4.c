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
		remodel, smithy, village, baron, great_hall
	};

	struct gameState G;

	printf("Testing whoseTurn.\n\n");

	printf("Test 1: First turn goes to first player.\n");
	initializeGame(2, k, 1, &G);
	
	assertTrue(whoseTurn(&G), 0);

	printf("Test 2: Turn increments after endTurn().\n");
	endTurn(&G);
	assertTrue(whoseTurn(&G), 1);

	printf("Test 3: Turn loops back to 0 after endTurn().\n");
	endTurn(&G);
	assertTrue(whoseTurn(&G), 0);


	return 0;
}
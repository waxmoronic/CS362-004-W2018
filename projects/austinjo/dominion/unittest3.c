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

	printf("Testing supplyCount.\n\n");

	printf("Test 1: Province pile with 2 players should be 8\n");
	initializeGame(2, k, 1, &G);
	assertTrue(supplyCount(province, &G), G.supplyCount[province]);

	printf("Test 2: Province pile with 3 players should be 12\n");
	initializeGame(3, k, 1, &G);
	assertTrue(supplyCount(province, &G), G.supplyCount[province]);

	printf("Test 3: Empty supply pile should return 0\n");
	G.supplyCount[province] = 0;
	assertTrue(supplyCount(province, &G), G.supplyCount[province]);

	printf("Test 4: Card not in game should return -1\n");
	assertTrue(supplyCount(sea_hag, &G), G.supplyCount[sea_hag]);

	return 0;
}
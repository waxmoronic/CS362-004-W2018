#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include "rngs.h"

void assertTrue(int ret, int exp) {
	if (ret == exp) printf ("Passed! Return matches expected!\n");
	else printf ("***FAILED: Return does not match, returned %d, expected %d\n", ret, exp);
}

int main () {

	int k[10] = {adventurer, council_room, feast, gardens, mine,
	             remodel, smithy, village, baron, great_hall
	            };

	struct gameState G;

	printf ("Testing isGameOver.\n\n");

	printf ("Test 1: Initialized Game\n\n");

	initializeGame(2, k, 1, &G);
	assertTrue(isGameOver(&G), 0);

	printf("Test 2: Province Pile Empty\n\n");

	G.supplyCount[province] = 0;
	assertTrue(isGameOver(&G), 1);

	G.supplyCount[province] = 8;
	
printf("Test 3: 3 Piles Empty\n\n");

	G.supplyCount[adventurer] = 0;
	printf("One pile emptied\n");
	assertTrue(isGameOver(&G), 0);

	G.supplyCount[council_room] = 0;
	printf("Two piles emptied\n");
	assertTrue(isGameOver(&G), 0);

	G.supplyCount[feast] = 0;
	printf("Three piles emptied\n");
	assertTrue(isGameOver(&G), 1);

	return 0;
}
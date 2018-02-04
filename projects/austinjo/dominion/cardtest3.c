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
	
	int i;

	struct gameState G;

	printf("Testing Gardens.\n");

	initializeGame(2, k, 1, &G);
	

	printf("Adding Gardens to player 0's hand.\n\n");
	gainCard(gardens, &G, 2, 0);
	
	printf("Test 1: Player should have 4 points (3 estate, 1 gardens).\n");
	assertTrue(scoreFor(0, &G), 4);

	printf("Adding 9 coppers to player 0's discard.\n\n");
	for (i = 0; i < 10; i++) gainCard(copper, &G, 0, 0);

	printf("Test 2: Player should have 5 points (3 estate, 2 gardens (20 cards)).\n");
	assertTrue(scoreFor(0, &G), 5);

	printf("Adding 10 coppers to player 0's deck.\n\n");
	for (i = 0; i < 11; i++) gainCard(copper, &G, 1, 0);

	printf("Test 3: Player should have 6 points (3 estate, 3 gardens (30 cards)).\n");
	assertTrue(scoreFor(0, &G), 5);

	return 0;
	}
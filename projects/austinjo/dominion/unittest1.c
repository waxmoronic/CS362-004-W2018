#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include "rngs.h"



int main () {

	int i, toFlag, p, r;


	int k[10] = {adventurer, council_room, feast, gardens, mine,
	             remodel, smithy, village, baron, great_hall
	            };

	struct gameState G;
	struct gameState pre;



	printf ("Testing gainCard.\n\n");

	printf ("Test 1: Empty supply pile\n\n");
// loop through each card with an empty deck, expect return = -1
	for (i = 0; i < 10; i++) {

		if (gainCard(k[i], &G, 1, 1) == -1) {
			printf("Passed, returned -1 for card %d\n", i);
		}
		else {
			printf("***FAILED, did not return -1 for card %d\n", i);
		}
	}

	printf ("Initializing game\n");
	initializeGame(2, k, 1, &G);
	printf("Test 2: Gain a card not in supply piles\n\n");

	if (gainCard(17, &G, 1, 1) == -1) {
		printf("Passed, returned -1\n");
	}
	else {
		printf("***FAILED, did not return -1\n");
	}

	printf ("Test 3: Each player gain a card from each position to discard, deck, and hand, supply pile reduced\n\n");
// loop through each card with an empty deck, expect return = -1
	for (i = 7; i < 17; i++) {
		for (toFlag = 0; toFlag < 3; toFlag++) {
			for (p = 0; p < 2; p++) {
				printf("Adding card %d, toFlag = %d, player = %d\n", i, toFlag, p);
				memcpy (&pre, &G, sizeof(struct gameState));
				gainCard(i, &G, toFlag, p);

				if (toFlag == 0) {
					if ((G.discard[p][G.discardCount[p] - 1] == i) && (G.discardCount[p] == pre.discardCount[p] + 1)) {
						printf ("Passed, card %d successfully added to player %d's discard\n", i, p);
					}
					else printf ("***FAILED, card %d not added to player %d's discard, was %d now %d\n", i, p, pre.discardCount[p], G.discardCount[p]);
				}

				else if (toFlag == 1) {
					if ((G.deck[p][G.deckCount[p]-1] == i) && (G.deckCount[p] == pre.deckCount[p] + 1)) {
						printf ("Passed, card %d successfully added to player %d's deck\n", i, p);
					}
					else printf ("***FAILED, card %d not added to player %d's deck\n", i, p);
				}

				else if (toFlag == 2) {
					if ((G.hand[p][G.handCount[p]-1] == i) && (G.handCount[p] == pre.handCount[p] + 1)) {
						printf ("Passed, card %d successfully added to player %d's hand\n", i, p);
					}
					else printf ("***FAILED, card %d not added to player %d's hand\n", i, p);
				}

				if (G.supplyCount[i] == pre.supplyCount[i] - 1) {
					printf ("Passed, supply for card %d reduced by 1\n", i);
				}
				else printf ("FAILED, supply for card %d not reduced by 1\n", i);
			}

		}

	}

	return 0;
}
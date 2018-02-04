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
	
	int pre_hand_size, post_hand_size, last_card, second_last_card;

	struct gameState G, pre;

	printf("Testing Adventurer.\n");

	initializeGame(2, k, 1, &G);

	printf("Adding Adventurer to player 0's hand.\n");
	gainCard(adventurer, &G, 2, 0);
	memcpy(&pre, &G, sizeof(struct gameState));
	
	printf("Playing Adventurer.\n");
	playCard(5, -1, -1, -1, &G);

	printf("Test 1: Player has +2 cards.\n");
	assertTrue(post_hand_size = G.handCount[0], pre_hand_size = pre.handCount[0] + 2);

	printf("Test 2: Two added cards are both treasure.\n");
	last_card = G.hand[0][G.handCount[0] - 1];
	second_last_card = G.hand[0][G.handCount[0] - 2];
	if ((last_card == 4 || last_card == 5 || last_card == 6) && (second_last_card == 4 || second_last_card == 5 || second_last_card == 6)) printf ("Passed! Last two cards are %d and %d.\n", last_card, second_last_card);
	else printf("***FAILED: Last two cards in hand are %d and %d.\n", last_card, second_last_card);

	printf("Test 3: Player has -1 action.\n");
	assertTrue(G.numActions, pre.numActions - 1);

	printf("Test 4: Player's discard pile is larger, or they only drew 2 cards.\n");
	
	if (G.discardCount[0] > pre.discardCount[0]  || ((G.deckCount[0] == pre.deckCount[0]-2) || G.discardCount[0] == 0 || G.discardCount[0] == 1)) printf ("Success! Discard pile went from %d to %d.\n", pre.discardCount[0], G.discardCount[0]);
	else printf("***FAILED: ");

	return 0;
	}
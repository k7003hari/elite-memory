const cards = document.querySelectorAll(".card");
let matched = 0;
let attempts = 0;
let cardOne, cardTwo;
let disableDeck = false;
let score = 0;
let highestScore = 0;

function flipCard({ target: clickedCard }) {
    if (cardOne !== clickedCard && !disableDeck) {
        clickedCard.classList.add("flip");
        if (!cardOne) {
            return (cardOne = clickedCard);
        }
        cardTwo = clickedCard;
        disableDeck = true;
        let cardOneImg = cardOne.querySelector(".back-view img").src,
            cardTwoImg = cardTwo.querySelector(".back-view img").src;
        matchCards(cardOneImg, cardTwoImg);
    }
}

function matchCards(img1, img2) {
    attempts++; // Count each attempt
    document.getElementById("attempts").innerText = `Attempts: ${attempts}`;

    if (img1 === img2) {
        matched++;
        score += 5; // Award 5 points for a correct match
        document.getElementById("score").innerText = `Score: ${score}`;
        if (matched == 8) {
            updateHighestScore(); // Update highest score
            setTimeout(() => {
                endGame();
            }, 1000);
        }
        cardOne.removeEventListener("click", flipCard);
        cardTwo.removeEventListener("click", flipCard);
        cardOne = cardTwo = "";
        disableDeck = false;
    } else {
        score -= 1; // Deduct 1 point for an incorrect match
        document.getElementById("score").innerText = `Score: ${score}`;

        setTimeout(() => {
            cardOne.classList.add("shake");
            cardTwo.classList.add("shake");
        }, 400);

        setTimeout(() => {
            cardOne.classList.remove("shake", "flip");
            cardTwo.classList.remove("shake", "flip");
            cardOne = cardTwo = "";
            disableDeck = false;
        }, 1200);
    }
}

function resetGame() {
    matched = 0;
    attempts = 0;
    score = 0;
    document.getElementById("score").innerText = "Score: 0";
    document.getElementById("attempts").innerText = "Attempts: 0";
    shuffleCard();
}

function shuffleCard() {
    disableDeck = false;
    cardOne = cardTwo = "";
    let arr = [1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8];
    arr.sort(() => (Math.random() > 0.5 ? 1 : -1));
    cards.forEach((card, i) => {
        card.classList.remove("flip");
        let imgTag = card.querySelector(".back-view img");
        imgTag.src = `images/img-${arr[i]}.png`;
        card.addEventListener("click", flipCard);
    });
}

function updateHighestScore() {
    if (score > highestScore) {
        highestScore = score;
        document.getElementById("highestScore").innerText = `Highest Score: ${highestScore} (Attempts: ${attempts})`;
    }
}

function endGame() {
    updateHighestScore();
    // Display endgame modal or message
}

shuffleCard();

cards.forEach((card) => {
    card.addEventListener("click", flipCard);
});

// Replay button functionality
document.getElementById("replayButton").addEventListener("click", () => {
    resetGame();
    updateHighestScore();
});

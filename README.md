# Word-Ladder-Game

## Description
The **Word Ladder Game** is a Java-based game where players connect words by changing one letter at a time. The game offers three difficulty levels, with the number of words to guess increasing as the difficulty rises. At the basic level, players guess 3 words, and the game progresses with more challenging sequences at higher levels.

The game uses the **Breadth-First Search (BFS)** algorithm to find the shortest path between words by changing one letter at a time. **ArrayLists** are used for storing words and managing gameplay.

## Features
- **Three Difficulty Levels**: Easy (3 words), Medium (5 words), and Hard (7 words).
- **BFS Algorithm**: Efficient word transformation to find the shortest path.
- **Word Management**: Uses ArrayLists for storing and managing word sequences.
  
## Gameplay
- Start with a given word and transform it into another by changing one letter at a time.
- At each difficulty level, guess the correct words in the sequence to complete the word ladder.
- Valid transformations differ by only one letter, and the game ensures that the sequence is logically correct.

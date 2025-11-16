# Hypernym Extraction Engine (Java NLP Project)

A lightweight Java tool that scans raw text, matches it against common linguistic patterns, and extracts hypernym–hyponym relations.  
In simple terms: it finds which word is a general category (hypernym) and which words are specific instances of it (hyponyms).

---

## Overview

This project implements a pattern-based approach to semantic relation extraction.  
It processes plain-text corpora, identifies predefined lexico-syntactic patterns (e.g., *"X such as Y"*), and outputs structured semantic pairs.

The system demonstrates classical NLP techniques without machine learning — just clean text parsing and smart rule-based pattern matching.

---

## Features

- Scans text files and processes them sentence by sentence  
- Applies known linguistic patterns to detect hypernym–hyponym relations:
  - `"X such as Y"`
  - `"Y and other X"`
  - `"X including Y"`
  - `"X especially Y"`
- Extracts and aggregates semantic relations
- Produces clean output in the form:

Output pairs:
  tool → hammer
  tool → screwdriver

How It Works

1. The program receives a path to a corpus (directory or single text file).

2. All files are read and split into sentences.

3. Each sentence is tested against several predefined patterns.

4. For each matched pattern:

    Extract the hypernym term

    Extract the hyponyms

5. Add the pairs to an internal relation structure

6. Output all discovered relations to a text file (or console)

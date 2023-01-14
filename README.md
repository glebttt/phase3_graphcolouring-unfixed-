### Notes:

1. Classes should always be in their own file (import issues!)
2. Classes should always have `CapitalCamelCase` names
3. Avoid println debugging ... it is the devil!  JUnit testing should be used
4. If code is useful, it should be in the file. If it isn't useful, it should be removed.  Avoid the purgatory of the "commented out code"
5. Classes should do one job.  A class that does two things might need to be two classes.
6. You do not always need to make an object - consider abstract classes!
7. If you do need to build objects, limit their scope as much as possible, global objects eat up RAM
8. Methods should do one task, and should avoid modifying state if possible
9. Add e.printStackTrace() when handling exceptions to allow for developer feedback.
10. Nesting statements over and over is bad practice, clean up deeply nested statements (either pull out the code into its own function or use dependency inversion magic).
11. Where possible, for high RAM computation, use the primitive types if you can (`int[]` is faster and smaller than `ArrayList<Integer>`)
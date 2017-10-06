# legacy-update-bug-mcve

1. Run `MorphologicalFiltersMain.main()` (*imagej-legacy* is not in the POM per default)
2. Activate and deactivate *Show Preview* in the dialog multiple times
3. See how the content of the *Preview* image changes
4. Add *imagej-legacy* to the POM:
```
<dependency>
	<groupId>net.imagej</groupId>
	<artifactId>imagej-legacy</artifactId>
</dependency>
```
5. Run again and see that image does not update in the legacy UI

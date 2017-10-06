# legacy-update-bug-mcve

1. Run `MorphologicalFiltersMain.main()` without *imagej-legacy* in the POM
2. Activate and deactivate *Show Preview* in the dialog multiple times
3. See how the content of the *Preview* image changes
4. Remove *imagej-legacy* from the POM --> image does not update in the legacy UI
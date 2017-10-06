/*-
 * #%L
 * Mathematical morphology library and plugins for ImageJ/Fiji.
 * %%
 * Copyright (C) 2014 - 2017 INRA.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */
package net.imagej;


import net.imagej.display.DatasetView;
import net.imagej.ops.OpService;
import net.imglib2.IterableInterval;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.algorithm.neighborhood.RectangleShape;
import net.imglib2.img.Img;
import net.imglib2.type.numeric.RealType;

import org.scijava.ItemIO;
import org.scijava.command.Command;
import org.scijava.command.DynamicCommand;
import org.scijava.command.Previewable;
import org.scijava.display.Display;
import org.scijava.display.DisplayService;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

@Plugin(type=Command.class, menuPath="Plugins>MorphoLibJ>Morphological Filters (Ops)")
public class MorphologicalFilters<T extends RealType<T>> extends DynamicCommand implements Previewable
{

	/** need to keep the instance of ImagePlus */
	@Parameter
	private Img<T> imagePlus;

	@Parameter(type = ItemIO.BOTH)
	private DatasetView view;

	@Parameter(label="Show preview", callback = "previewChanged")
	boolean showPreview = false;
	
	@Parameter
	private OpService opService;
	
	@Parameter
	DisplayService displayService;
	
	private Display<?> previewDisplay;
	
	@Override
	public void run()
	{
		// NB
	}

	private int counter = 1;
	
	public void previewChanged() {
		RandomAccessibleInterval<? extends RealType<?>> plane = view.xyPlane();
		
		RandomAccessibleInterval copy = opService.copy().rai(plane);
		IterableInterval eroded = opService.morphology().erode(copy, new RectangleShape(counter++, true));
		
		if (previewDisplay == null) {
			previewDisplay = displayService.createDisplay("Preview", eroded);
		} else {
			previewDisplay.clear();
			previewDisplay.display(eroded);
			previewDisplay.update();
		}
		
	}
}

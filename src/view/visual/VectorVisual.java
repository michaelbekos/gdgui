package view.visual;

import com.yworks.yfiles.algorithms.YPoint;
import com.yworks.yfiles.algorithms.YVector;
import com.yworks.yfiles.graph.INode;
import com.yworks.yfiles.view.GraphComponent;
import com.yworks.yfiles.view.IRenderContext;
import com.yworks.yfiles.view.IVisual;
import com.yworks.yfiles.view.IVisualCreator;

import java.awt.*;

/**
 * Corresponds to a drawable vector.
 *
 * @author  Michael Bekos
 */
public class VectorVisual implements IVisual, IVisualCreator {

    /** Instance Variables */
    private GraphComponent view;
    private YVector vector;
    private INode node;
    private Color color;

    /**
     * Creates a new instance of EnclosingRectangle by setting the given
     * color as the color of it boundary.
     */
    public VectorVisual(GraphComponent view, YVector vector, INode node, Color color)
    {
        this.view = view;
        this.vector = new YVector(vector);
        this.vector.scale(100);
        this.node = node;
        this.color = color;
    }

    @Override
    public void paint(IRenderContext iRenderContext, Graphics2D graphics2D) {
        int bottomLeftX = (int) this.node.getLayout().getCenter().x;
        int bottomLeftY = (int) this.node.getLayout().getCenter().y;

        YPoint topRight = YVector.add(new YPoint(bottomLeftX, bottomLeftY), vector);

        int topRightX = (int) topRight.getX();
        int topRightY = (int) topRight.getY();

        graphics2D.setColor(this.color);
        graphics2D.drawLine(bottomLeftX, bottomLeftY, topRightX, topRightY);

    }

    /**
     * Returns the bounds of this drawable.
     */
    public java.awt.Rectangle getBounds()
    {
        double bottomLeftX = this.node.getLayout().getCenter().x;
        double bottomLeftY = this.node.getLayout().getCenter().y;

        YPoint topRight = YVector.add(new YPoint(bottomLeftX, bottomLeftY), vector);

        double topRightX = topRight.getX();
        double topRightY = topRight.getY();

        return new java.awt.Rectangle((int) Math.min(bottomLeftX, topRightX),
                (int) Math.min(bottomLeftY, topRightY),
                (int) Math.abs(bottomLeftX - topRight.getX()),
                (int) Math.abs(bottomLeftY - topRight.getY()));
    }


    @Override
    public IVisual createVisual(IRenderContext iRenderContext) {
        return this;
    }

    @Override
    public IVisual updateVisual(IRenderContext iRenderContext, IVisual iVisual) { return this; }
}

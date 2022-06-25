package ru.vad1mchk.progr.lab05.client.util

import javafx.animation.FadeTransition
import javafx.application.Platform
import javafx.event.EventHandler
import javafx.geometry.Point2D
import javafx.scene.canvas.Canvas
import javafx.scene.layout.Region
import javafx.scene.paint.Color
import javafx.util.Duration
import ru.vad1mchk.progr.lab05.client.application.SpaceMarineInformation
import ru.vad1mchk.progr.lab05.client.styles.Colors
import ru.vad1mchk.progr.lab05.common.datatypes.User
import tornadofx.SVGIcon
import tornadofx.add
import java.util.LinkedList
import kotlin.math.exp

class RegionPlacer(val region: Region) {
    companion object {
        var flatSpaceMarines = LinkedList<FlatSpaceMarine>()
            set(newFlatSpaceMarines) {
                field.clear()
                field.addAll(newFlatSpaceMarines)
            }
        const val DEFAULT_SIZE = 96.0
    }
    fun place(newFlatSpaceMarines: LinkedList<FlatSpaceMarine>) {
        for (flatSpaceMarine in newFlatSpaceMarines) {
            draw(flatSpaceMarine)
        }
        flatSpaceMarines.clear()
        flatSpaceMarines.addAll(newFlatSpaceMarines)
    }

    fun clear(user: User) {
        region.childrenUnmodifiable.clear()
        for (flatSpaceMarine in flatSpaceMarines.also {
            it.removeIf { spaceMarine -> spaceMarine.creatorName != user.userName }
        }) {
            draw(flatSpaceMarine)
        }
    }

    private fun coordinates(flatSpaceMarine: FlatSpaceMarine): Point2D {
        return Point2D(flatSpaceMarine.coordinatesX.toDouble(), flatSpaceMarine.coordinatesY.toDouble())
    }

    private fun size(flatSpaceMarine: FlatSpaceMarine): Double {
        return DEFAULT_SIZE / (1 + exp(-flatSpaceMarine.health))
    }

    private fun color(flatSpaceMarine: FlatSpaceMarine): Color {
        return Colors.colorRgbFromHashCode(flatSpaceMarine.creatorName)
    }

    private fun draw(flatSpaceMarine: FlatSpaceMarine) {
        val svgIcon = SVGIcon(Configuration.spaceMarineDrawingPath, size(flatSpaceMarine), color(flatSpaceMarine))
        coordinates(flatSpaceMarine).also {
            svgIcon.layoutX = it.x
            svgIcon.layoutY = it.y
        }
        svgIcon.onMouseClicked = EventHandler {
            SpaceMarineInformation(flatSpaceMarine).draw()
        }
        Platform.runLater {
            region.add(svgIcon)
        }
    }
}
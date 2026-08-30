/* SPDX-License-Identifier: MIT */
package io.github.janguenter.bluemap.resource.athena.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class ChippedOriginParityTest {

    private static final int[] INTEGER_CASES = {
            Integer.MIN_VALUE, -2, -1, 0, 1, 2, Integer.MAX_VALUE
    };

    @Test
    void everyTextureRoleMatchesTheFrozenOrigin() {
        List<String> shared = Arrays.stream(CtmTextureRole.values())
                .map(role -> role.name() + ":" + role.wireName())
                .toList();
        List<String> origin = Arrays.stream(io.github.janguenter.bluemap.chipped.model.CtmTextureRole.values())
                .map(role -> role.name() + ":" + role.wireName())
                .toList();
        assertEquals(origin, shared);
    }

    @Test
    void everySelectorInputMatchesTheFrozenOrigin() {
        for (boolean vertical : List.of(false, true)) {
            for (boolean horizontal : List.of(false, true)) {
                for (boolean diagonal : List.of(false, true)) {
                    assertEquals(
                            io.github.janguenter.bluemap.chipped.model.CtmSelector
                                    .select(vertical, horizontal, diagonal).name(),
                            CtmSelector.select(vertical, horizontal, diagonal).name()
                    );
                }
            }
        }
    }

    @Test
    void everyUnsignedConnectionMaskMatchesTheFrozenOrigin() {
        for (int mask = 0; mask <= 0xFF; mask++) {
            CtmConnections shared = CtmConnections.fromMask(mask);
            io.github.janguenter.bluemap.chipped.model.CtmConnections origin =
                    io.github.janguenter.bluemap.chipped.model.CtmConnections.fromMask(mask);

            assertEquals(origin.mask(), shared.mask(), "mask " + mask);
            assertEquals(origin.up(), shared.up(), "up " + mask);
            assertEquals(origin.down(), shared.down(), "down " + mask);
            assertEquals(origin.left(), shared.left(), "left " + mask);
            assertEquals(origin.right(), shared.right(), "right " + mask);
            assertEquals(origin.upLeft(), shared.upLeft(), "upLeft " + mask);
            assertEquals(origin.upRight(), shared.upRight(), "upRight " + mask);
            assertEquals(origin.downLeft(), shared.downLeft(), "downLeft " + mask);
            assertEquals(origin.downRight(), shared.downRight(), "downRight " + mask);
            assertEquals(origin.completelyConnected(), shared.completelyConnected(), "complete " + mask);
            assertEquals(
                    origin.quadrants().stream().map(Enum::name).toList(),
                    shared.quadrants().stream().map(Enum::name).toList(),
                    "quadrants " + mask
            );
        }
    }

    @Test
    void invalidConnectionMasksMatchTheFrozenOrigin() {
        for (int mask : new int[] {Integer.MIN_VALUE, -1, 0x100, Integer.MAX_VALUE}) {
            IllegalArgumentException shared = assertThrows(
                    IllegalArgumentException.class,
                    () -> CtmConnections.fromMask(mask)
            );
            IllegalArgumentException origin = assertThrows(
                    IllegalArgumentException.class,
                    () -> io.github.janguenter.bluemap.chipped.model.CtmConnections.fromMask(mask)
            );
            assertEquals(origin.getMessage(), shared.getMessage());
        }
    }

    @Test
    void everyFaceAndVectorOperationMatchesTheFrozenOrigin() {
        for (CubeFace shared : CubeFace.values()) {
            io.github.janguenter.bluemap.chipped.model.CubeFace origin =
                    io.github.janguenter.bluemap.chipped.model.CubeFace.valueOf(shared.name());
            assertVectorEquals(origin.normal(), shared.normal());
            assertVectorEquals(origin.uvRight(), shared.uvRight());
            assertVectorEquals(origin.uvUp(), shared.uvUp());
            assertVectorEquals(origin.localUp(), shared.localUp());
            assertVectorEquals(origin.localDown(), shared.localDown());
            assertVectorEquals(origin.localRight(), shared.localRight());
            assertVectorEquals(origin.localLeft(), shared.localLeft());
        }

        for (int x : INTEGER_CASES) {
            for (int y : INTEGER_CASES) {
                for (int z : INTEGER_CASES) {
                    CubeFace.Vec shared = new CubeFace.Vec(x, y, z);
                    io.github.janguenter.bluemap.chipped.model.CubeFace.Vec origin =
                            new io.github.janguenter.bluemap.chipped.model.CubeFace.Vec(x, y, z);
                    for (int factor : INTEGER_CASES) {
                        assertVectorEquals(origin.scale(factor), shared.scale(factor));
                    }
                    for (int addend : INTEGER_CASES) {
                        assertVectorEquals(
                                origin.add(new io.github.janguenter.bluemap.chipped.model.CubeFace.Vec(
                                        addend, -addend, addend
                                )),
                                shared.add(new CubeFace.Vec(addend, -addend, addend))
                        );
                    }
                }
            }
        }
    }

    private static void assertVectorEquals(
            io.github.janguenter.bluemap.chipped.model.CubeFace.Vec origin,
            CubeFace.Vec shared
    ) {
        assertEquals(origin.x(), shared.x());
        assertEquals(origin.y(), shared.y());
        assertEquals(origin.z(), shared.z());
    }
}

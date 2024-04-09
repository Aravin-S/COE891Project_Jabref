package org.jabref.cli;

import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JabRefCLITests {
    private final String bibtex = "@article{test, title=\"test title\"}";

    @Test
    void emptyCLILeftOversLongOptions() throws Exception {
        JabRefCLI cli = new JabRefCLI(new String[]{"--nogui", "--import=some/file", "--output=some/export/file"});

        assertEquals(Collections.emptyList(), cli.getLeftOver());
    }

    @Test
    void guiIsDisabledLongOptions() throws Exception {
        JabRefCLI cli = new JabRefCLI(new String[]{"--nogui", "--import=some/file", "--output=some/export/file"});
        assertTrue(cli.isDisableGui());
    }

    @Test
    void successfulParsingOfFileImportCLILongOptions() throws Exception {
        JabRefCLI cli = new JabRefCLI(new String[]{"--nogui", "--import=some/file", "--output=some/export/file"});

        assertEquals("some/file", cli.getFileImport());
    }

    @Test
    void successfulParsingOfFileExportCLILongOptions() throws Exception {
        JabRefCLI cli = new JabRefCLI(new String[]{"--nogui", "--import=some/file", "--output=some/export/file"});

        assertEquals("some/export/file", cli.getFileExport());
    }

    @Test
    void emptyCLILeftOversShortOptions() throws Exception {
        JabRefCLI cli = new JabRefCLI(new String[]{"-n", "-i=some/file", "-o=some/export/file"});

        assertEquals(Collections.emptyList(), cli.getLeftOver());
    }

    @Test
    void guiIsDisabledShortOptions() throws Exception {
        JabRefCLI cli = new JabRefCLI(new String[]{"-n", "-i=some/file", "-o=some/export/file"});

        assertTrue(cli.isDisableGui());
    }

    @Test
    void successfulParsingOfFileImportShortOptions() throws Exception {
        JabRefCLI cli = new JabRefCLI(new String[]{"-n", "-i=some/file", "-o=some/export/file"});

        assertEquals("some/file", cli.getFileImport());
    }

    @Test
    void successfulParsingOfFileExportShortOptions() throws Exception {
        JabRefCLI cli = new JabRefCLI(new String[]{"-n", "-i=some/file", "-o=some/export/file"});

        assertEquals("some/export/file", cli.getFileExport());
    }

    @Test
    void emptyPreferencesLeftOver() throws Exception {
        JabRefCLI cli = new JabRefCLI(new String[]{"-n", "-x=some/file"});

        assertEquals(Collections.emptyList(), cli.getLeftOver());
    }

    @Test
    void successfulExportOfPreferences() throws Exception {
        JabRefCLI cli = new JabRefCLI(new String[]{"-n", "-x=some/file"});

        assertEquals("some/file", cli.getPreferencesExport());
    }

    @Test
    void guiDisabledForPreferencesExport() throws Exception {
        JabRefCLI cli = new JabRefCLI(new String[]{"-n", "-x=some/file"});

        assertTrue(cli.isDisableGui());
    }

    @Test
    void emptyLeftOversCLIShortImportingBibtex() throws Exception {
        JabRefCLI cli = new JabRefCLI(new String[]{"-ib", bibtex});

        assertEquals(Collections.emptyList(), cli.getLeftOver());
    }

    @Test
    void recognizesImportBibtexShort() throws Exception {
        JabRefCLI cli = new JabRefCLI(new String[]{"-ib", bibtex});

        assertTrue(cli.isBibtexImport());
    }

    @Test
    void successfulParsingOfBibtexImportShort() throws Exception {
        JabRefCLI cli = new JabRefCLI(new String[]{"-ib", bibtex});

        assertEquals(bibtex, cli.getBibtexImport());
    }

    @Test
    void emptyLeftOversCLILongImportingBibtex() throws Exception {
        JabRefCLI cli = new JabRefCLI(new String[]{"-importBibtex", bibtex});

        assertEquals(Collections.emptyList(), cli.getLeftOver());
    }

    @Test
    void recognizesImportBibtexLong() throws Exception {
        JabRefCLI cli = new JabRefCLI(new String[]{"-importBibtex", bibtex});

        assertTrue(cli.isBibtexImport());
    }

    @Test
    void successfulParsingOfBibtexImportLong() throws Exception {
        JabRefCLI cli = new JabRefCLI(new String[]{"-importBibtex", bibtex});

        assertEquals(bibtex, cli.getBibtexImport());
    }

    @Test
    void wrapStringList1() {
        List<String> given = null;
        String expected = "Available export formats: ";
        assertThrows(NullPointerException.class, () -> JabRefCLI.wrapStringList(given, 26));
    }

    @Test
    void wrapStringList2() {
        List<String> given = List.of("html", "simplehtml", "docbook5", "docbook4", "din1505", "bibordf", "tablerefs", "listrefs",
                "harvard");
        String expected = """
                Available export formats: html, simplehtml, docbook5, docbook4, din1505, bibordf, tablerefs,
                listrefs, harvard""";
        String actual = JabRefCLI.wrapStringList(given, 26);
        System.out.println(actual);
        assertEquals(expected, "Available export formats: " + actual);
    }

    @Test
    void wrapStringList3() {
        List<String> given = List.of("html");
        String expected = """
                Available export formats: html""";
        String actual = JabRefCLI.wrapStringList(given, 26);
        System.out.println(actual);
        assertEquals(expected, "Available export formats: " + actual);
    }

    @Test
    void wrapStringList4() {
        List<String> given = List.of("html", "simplehtml", "docbook5", "docbook4", "din1505", "bibordf", "tablerefs", "listrefs");
        String expected = """
                Available export formats: html, simplehtml, docbook5, docbook4, din1505, bibordf, tablerefs,
                listrefs""";
        String actual = JabRefCLI.wrapStringList(given, 26);
        System.out.println(actual);
        assertEquals(expected, "Available export formats: " + actual);
    }

    @Test
    void alignStringTable1() {
        List<Pair<String, String>> given = null;
        assertThrows(NullPointerException.class, () -> JabRefCLI.alignStringTable(given));
    }

    @Test
    void alignStringTable2() {
        List<Pair<String, String>> given = List.of(
                new Pair<>("Apple", "Slice"));

        String expected = """
                Apple : Slice
                """;

        String normalizedExpected = normalizeLineEndings(expected);
        String normalizedActual = normalizeLineEndings(JabRefCLI.alignStringTable(given));

        assertEquals(normalizedExpected, normalizedActual);
    }

    @Test
    void alignStringTable3() {
        List<Pair<String, String>> given = List.of(
                new Pair<>("Apple", "Slice"),
                new Pair<>("Bread", "Loaf"));

        String expected = """
                Apple : Slice
                Bread : Loaf
                """;

        String normalizedExpected = normalizeLineEndings(expected);
        String normalizedActual = normalizeLineEndings(JabRefCLI.alignStringTable(given));

        assertEquals(normalizedExpected, normalizedActual);
    }

    // Method to normalize line endings (\r\n to \n)
    private String normalizeLineEndings(String input) {
        return input.replaceAll("\r\n", "\n");
    }
}

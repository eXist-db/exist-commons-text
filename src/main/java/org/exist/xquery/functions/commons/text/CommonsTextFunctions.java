/*
 * Apache Commons Text Functions - This is a library function module for eXist-db that wraps the specifically finding the similarities and distances between strings
 * Copyright © 2017 eXist-db (exit-open@lists.sourceforge.net)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.exist.xquery.functions.commons.text;

import org.apache.commons.text.similarity.*;

import org.exist.dom.QName;
import org.exist.xquery.*;
import org.exist.xquery.value.*;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static org.exist.xquery.FunctionDSL.*;
import static org.exist.xquery.functions.commons.text.CommonsTextModule.functionSignature;

/**
 * Some very simple XQuery example functions implemented
 * in Java.
 */
public class CommonsTextFunctions extends BasicFunction {

    private static final String COSINE_SIMILARITY = "cosine-similarity";

    public final static FunctionSignature FNS_COSINE_SIMILARITY = new FunctionSignature(
            new QName(COSINE_SIMILARITY, CommonsTextModule.NAMESPACE_URI, CommonsTextModule.PREFIX),
            "Measures the cosine similarity between two strings.",
            new SequenceType[]{
                    new FunctionParameterSequenceType("left", Type.STRING, Cardinality.EXACTLY_ONE, "The left string"),
                    new FunctionParameterSequenceType("right", Type.STRING, Cardinality.EXACTLY_ONE, "The right string"),
                    new FunctionParameterSequenceType("delimiter", Type.STRING, Cardinality.ZERO_OR_ONE, "The delimiter between the words")
            },
            new FunctionReturnSequenceType(Type.DOUBLE, Cardinality.EXACTLY_ONE, "the similarity score")
    );

    private static final String COSINE_DISTANCE = "cosine-distance";
    public final static FunctionSignature FNS_COSINE_DISTANCE = new FunctionSignature(
            new QName(COSINE_DISTANCE, CommonsTextModule.NAMESPACE_URI, CommonsTextModule.PREFIX),
            "Measures the cosine distance between two strings.",
            new SequenceType[]{
                    new FunctionParameterSequenceType("left", Type.STRING, Cardinality.EXACTLY_ONE, "The left string"),
                    new FunctionParameterSequenceType("right", Type.STRING, Cardinality.EXACTLY_ONE, "The right string")
            },
            new FunctionReturnSequenceType(Type.DOUBLE, Cardinality.EXACTLY_ONE, "the distance score")
    );

    private static final String HAMMING_DISTANCE = "hamming-distance";
    public final static FunctionSignature FNS_HAMMING_DISTANCE = new FunctionSignature(
            new QName(HAMMING_DISTANCE, CommonsTextModule.NAMESPACE_URI, CommonsTextModule.PREFIX),
            "Measures the hamming distance between two strings.",
            new SequenceType[]{
                    new FunctionParameterSequenceType("left", Type.STRING, Cardinality.EXACTLY_ONE, "The left string"),
                    new FunctionParameterSequenceType("right", Type.STRING, Cardinality.EXACTLY_ONE, "The right string")
            },
            new FunctionReturnSequenceType(Type.INTEGER, Cardinality.EXACTLY_ONE, "the distance score")
    );

    private static final String JACCARD_SIMILARITY = "jaccard-similarity";
    public final static FunctionSignature FNS_JACCARD_SIMILARITY = new FunctionSignature(
            new QName(JACCARD_SIMILARITY, CommonsTextModule.NAMESPACE_URI, CommonsTextModule.PREFIX),
            "Measures the Jaccard similarity between two strings.",
            new SequenceType[]{
                    new FunctionParameterSequenceType("left", Type.STRING, Cardinality.EXACTLY_ONE, "The left string"),
                    new FunctionParameterSequenceType("right", Type.STRING, Cardinality.EXACTLY_ONE, "The right string")
            },
            new FunctionReturnSequenceType(Type.DOUBLE, Cardinality.EXACTLY_ONE, "the similarity score")
    );

    private static final String JACCARD_DISTANCE = "jaccard-distance";
    public final static FunctionSignature FNS_JACCARD_DISTANCE = new FunctionSignature(
            new QName(JACCARD_DISTANCE, CommonsTextModule.NAMESPACE_URI, CommonsTextModule.PREFIX),
            "Measures the Jaccard distance between two strings.",
            new SequenceType[]{
                    new FunctionParameterSequenceType("left", Type.STRING, Cardinality.EXACTLY_ONE, "The left string"),
                    new FunctionParameterSequenceType("right", Type.STRING, Cardinality.EXACTLY_ONE, "The right string")
            },
            new FunctionReturnSequenceType(Type.DOUBLE, Cardinality.EXACTLY_ONE, "the distance score")
    );

    private static final String JARO_WINKLER_SIMILARITY = "jaro-winkler-similarity";
    public final static FunctionSignature FNS_JARO_WINKLER_SIMILARITY = new FunctionSignature(
            new QName(JARO_WINKLER_SIMILARITY, CommonsTextModule.NAMESPACE_URI, CommonsTextModule.PREFIX),
            "Measures the Jaro Winkler similarity between two strings.",
            new SequenceType[]{
                    new FunctionParameterSequenceType("left", Type.STRING, Cardinality.EXACTLY_ONE, "The left string"),
                    new FunctionParameterSequenceType("right", Type.STRING, Cardinality.EXACTLY_ONE, "The right string")
            },
            new FunctionReturnSequenceType(Type.DOUBLE, Cardinality.EXACTLY_ONE, "the similarity score")
    );

    private static final String JARO_WINKLER_DISTANCE = "jaro-winkler-distance";
    public final static FunctionSignature FNS_JARO_WINKLER_DISTANCE = new FunctionSignature(
            new QName(JARO_WINKLER_DISTANCE, CommonsTextModule.NAMESPACE_URI, CommonsTextModule.PREFIX),
            "Measures the Jaro Winkler distance between two strings.",
            new SequenceType[]{
                    new FunctionParameterSequenceType("left", Type.STRING, Cardinality.EXACTLY_ONE, "The left string"),
                    new FunctionParameterSequenceType("right", Type.STRING, Cardinality.EXACTLY_ONE, "The right string")
            },
            new FunctionReturnSequenceType(Type.DOUBLE, Cardinality.EXACTLY_ONE, "the distance score")
    );

    private static final String LONGEST_COMMON_SUBSEQUENCE = "longest-common-subsequence";
    public final static FunctionSignature FNS_LONGEST_COMMON_SUBSEQUENCE = new FunctionSignature(
            new QName(LONGEST_COMMON_SUBSEQUENCE, CommonsTextModule.NAMESPACE_URI, CommonsTextModule.PREFIX),
            "Measures the longest common subsequence between two strings.",
            new SequenceType[]{
                    new FunctionParameterSequenceType("left", Type.STRING, Cardinality.EXACTLY_ONE, "The left string"),
                    new FunctionParameterSequenceType("right", Type.STRING, Cardinality.EXACTLY_ONE, "The right string")
            },
            new FunctionReturnSequenceType(Type.INTEGER, Cardinality.EXACTLY_ONE, "the longest common subsequence")
    );

    private static final String LONGEST_COMMON_SUBSEQUENCE_DISTANCE = "longest-common-subsequence-distance";
    public final static FunctionSignature FNS_LONGEST_COMMON_SUBSEQUENCE_DISTANCE = new FunctionSignature(
            new QName(LONGEST_COMMON_SUBSEQUENCE_DISTANCE, CommonsTextModule.NAMESPACE_URI, CommonsTextModule.PREFIX),
            "Measures the longest common subsequence distance between two strings.",
            new SequenceType[]{
                    new FunctionParameterSequenceType("left", Type.STRING, Cardinality.EXACTLY_ONE, "The left string"),
                    new FunctionParameterSequenceType("right", Type.STRING, Cardinality.EXACTLY_ONE, "The right string")
            },
            new FunctionReturnSequenceType(Type.INTEGER, Cardinality.EXACTLY_ONE, "the longest common subsequence distance")
    );

    public CommonsTextFunctions(final XQueryContext context, final FunctionSignature signature) {
        super(context, signature);
    }

    @Override
    public Sequence eval(final Sequence[] args, final Sequence contextSequence) throws XPathException {
        final StringValue leftValue = (StringValue) args[0].itemAt(0);
        final StringValue rightValue = (StringValue) args[1].itemAt(0);

        switch (getName().getLocalPart()) {

            case COSINE_SIMILARITY:
                final CosineSimilarity cs = new CosineSimilarity();

                String textDelimiter = " ";

                if (args.length > 2) {
                    textDelimiter = args[2].itemAt(0).getStringValue();
                }

                Map<CharSequence, Integer> leftVector =
                        Arrays.stream(leftValue.getStringValue().split(textDelimiter))
                                .collect(Collectors.toMap(c -> c, c -> 1, Integer::sum));
                Map<CharSequence, Integer> rightVector =
                        Arrays.stream(rightValue.getStringValue().split(textDelimiter))
                                .collect(Collectors.toMap(c -> c, c -> 1, Integer::sum));


                return new DoubleValue(cs.cosineSimilarity(leftVector, rightVector));

            case COSINE_DISTANCE:
                final CosineDistance cd = new CosineDistance();
                return new DoubleValue(cd.apply(leftValue.getStringValue(), rightValue.getStringValue()));

            case HAMMING_DISTANCE:
                final HammingDistance hd = new HammingDistance();
                return new IntegerValue(hd.apply(leftValue.getStringValue(), rightValue.getStringValue()));

            case JACCARD_SIMILARITY:
                final JaccardSimilarity js = new JaccardSimilarity();
                return  new DoubleValue(js.apply(leftValue.getStringValue(), rightValue.getStringValue()));

            case JACCARD_DISTANCE:
                final JaccardDistance jd = new JaccardDistance();
                return  new DoubleValue(jd.apply(leftValue.getStringValue(), rightValue.getStringValue()));

            case JARO_WINKLER_SIMILARITY:
                final JaroWinklerSimilarity jws = new JaroWinklerSimilarity();
                return new DoubleValue(jws.apply(leftValue.getStringValue(), rightValue.getStringValue()));

            case JARO_WINKLER_DISTANCE:
                final JaroWinklerDistance jwd = new JaroWinklerDistance();
                return new DoubleValue(jwd.apply(leftValue.getStringValue(), rightValue.getStringValue()));

            case LONGEST_COMMON_SUBSEQUENCE:
                final LongestCommonSubsequence lcs = new LongestCommonSubsequence();
                return new IntegerValue(lcs.apply(leftValue.getStringValue(), rightValue.getStringValue()));

            case LONGEST_COMMON_SUBSEQUENCE_DISTANCE:
                final LongestCommonSubsequenceDistance lcsd = new LongestCommonSubsequenceDistance();
                return new IntegerValue(lcsd.apply(leftValue.getStringValue(), rightValue.getStringValue()));

            default:
                throw new XPathException(this, "No function: " + getName() + "#" + getSignature().getArgumentCount());
        }
    }
}

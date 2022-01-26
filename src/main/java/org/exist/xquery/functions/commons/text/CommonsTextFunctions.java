package org.exist.xquery.functions.commons.text;

import org.apache.commons.text.similarity.CosineSimilarity;
import org.exist.xquery.BasicFunction;
import org.exist.xquery.FunctionSignature;
import org.exist.xquery.XPathException;
import org.exist.xquery.XQueryContext;
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
    static final FunctionSignature FS_COSINE_SIMILARITY = functionSignature(
            COSINE_SIMILARITY,
            "Measures the cosine similarity between two strings.",
            returns(Type.DOUBLE),
            param("left", Type.STRING, "Left"),
            param("right", Type.STRING, "Right"),
            optParam("delimiter", Type.STRING, "The optional text delimiter.  The default is a space.")
    );

    public CommonsTextFunctions(final XQueryContext context, final FunctionSignature signature) {
        super(context, signature);
    }

    @Override
    public Sequence eval(final Sequence[] args, final Sequence contextSequence) throws XPathException {
        switch (getName().getLocalPart()) {

            case COSINE_SIMILARITY:
                final CosineSimilarity cs = new CosineSimilarity();

                final StringValue leftValue = (StringValue) args[0].itemAt(0);
                final StringValue rightValue = (StringValue) args[1].itemAt(0);
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

            default:
                throw new XPathException(this, "No function: " + getName() + "#" + getSignature().getArgumentCount());
        }
    }
}

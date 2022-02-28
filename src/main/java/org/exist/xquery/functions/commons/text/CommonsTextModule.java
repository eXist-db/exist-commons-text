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

import org.exist.dom.QName;
import org.exist.xquery.*;
import org.exist.xquery.value.FunctionParameterSequenceType;
import org.exist.xquery.value.FunctionReturnSequenceType;

import java.util.List;
import java.util.Map;

import static org.exist.xquery.FunctionDSL.functionDefs;

/**
 * A very simple example XQuery Library Module implemented
 * in Java.
 */
public class CommonsTextModule extends AbstractInternalModule {

    public static final String NAMESPACE_URI = "http://exist-db.org/xquery/commons-text";
    public static final String PREFIX = "commons-text";
    public static final String RELEASED_IN_VERSION = "eXist-6.0.0";

    // register the functions of the module
    public static final FunctionDef[] functions = {
            new FunctionDef(CommonsTextFunctions.FNS_COSINE_SIMILARITY, CommonsTextFunctions.class),
            new FunctionDef(CommonsTextFunctions.FNS_COSINE_DISTANCE, CommonsTextFunctions.class),
            new FunctionDef(CommonsTextFunctions.FNS_HAMMING_DISTANCE, CommonsTextFunctions.class),
            new FunctionDef(CommonsTextFunctions.FNS_JACCARD_SIMILARITY, CommonsTextFunctions.class),
            new FunctionDef(CommonsTextFunctions.FNS_JACCARD_DISTANCE, CommonsTextFunctions.class),
            new FunctionDef(CommonsTextFunctions.FNS_JARO_WINKLER_SIMILARITY, CommonsTextFunctions.class),
            new FunctionDef(CommonsTextFunctions.FNS_JARO_WINKLER_DISTANCE, CommonsTextFunctions.class),
            new FunctionDef(CommonsTextFunctions.FNS_LONGEST_COMMON_SUBSEQUENCE, CommonsTextFunctions.class),
            new FunctionDef(CommonsTextFunctions.FNS_LONGEST_COMMON_SUBSEQUENCE_DISTANCE, CommonsTextFunctions.class)

    };

    public CommonsTextModule(final Map<String, List<? extends Object>> parameters) {
        super(functions, parameters);
    }

    @Override
    public String getNamespaceURI() {
        return NAMESPACE_URI;
    }

    @Override
    public String getDefaultPrefix() {
        return PREFIX;
    }

    @Override
    public String getDescription() {
        return "This is a library function module for eXist-db that wraps the specifically finding the similarities and distances between strings.";
    }

    @Override
    public String getReleaseVersion() {
        return RELEASED_IN_VERSION;
    }

    static FunctionSignature functionSignature(final String name, final String description,
            final FunctionReturnSequenceType returnType, final FunctionParameterSequenceType... paramTypes) {
        return FunctionDSL.functionSignature(new QName(name, NAMESPACE_URI), description, returnType, paramTypes);
    }

    static FunctionSignature[] functionSignatures(final String name, final String description,
            final FunctionReturnSequenceType returnType, final FunctionParameterSequenceType[][] variableParamTypes) {
        return FunctionDSL.functionSignatures(new QName(name, NAMESPACE_URI), description, returnType, variableParamTypes);
    }

    static class ExpathBinModuleErrorCode extends ErrorCodes.ErrorCode {
        private ExpathBinModuleErrorCode(final String code, final String description) {
            super(new QName(code, NAMESPACE_URI, PREFIX), description);
        }
    }
}
